package com.ssafy.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.OffGroupDao;
import com.ssafy.board.model.dao.OffUserMembershipDao;
import com.ssafy.board.model.dto.OffGroup;
import com.ssafy.board.model.dto.OffUserMembership;
import com.ssafy.board.model.dto.OnGroup;
import com.ssafy.board.model.dto.OnUserMembership;

@Service
public class OffGroupServiceImpl implements OffGroupService {
	@Autowired
	OffGroupDao offGroupDao;

	@Autowired
	OffUserMembershipDao offUserMemberShipDao;

	@Override
	public void registOffGroup(OffGroup offGroup) {
		offGroupDao.insertGroup(offGroup);
		OffUserMembership tmp = new OffUserMembership();
		tmp.setGroup_id(offGroup.getGroup_id());
		tmp.setUser_id(offGroup.getGroup_leader_user_id());
		offUserMemberShipDao.insertMembership(tmp);
	}

	@Override
	public void updateOffGroup(OffGroup offGroup) {
		offGroupDao.updateGroup(offGroup);
	}

	@Override
	public void deleteOffGroup(int group_id) {
		offGroupDao.deleteGroup(group_id);
	}

	@Override
	public List<OffGroup> selectOffGroupList() {
		return offGroupDao.selectAll();
	}

	@Override
	public boolean isJoin(String user_id, int group_id) {
		List<OffUserMembership> joinlist = offUserMemberShipDao.selectMembershipByUserId(user_id);
		for (int i = 0; i < joinlist.size(); i++) {
			OffUserMembership join = joinlist.get(i);
			if (join.getGroup_id() == group_id) {
				return true;
			}
		}
		return false;
	}
	@Override
	public List<OffGroup> getMyGroup(String user_id) {
		System.out.println("asdasdasd");
		List<OffUserMembership> tmpList=offUserMemberShipDao.selectMembershipByUserId(user_id);
		List<OffGroup> result=new ArrayList<OffGroup>();
		for(OffUserMembership us : tmpList) {
			result.add(offGroupDao.selectOneGroup(us.getGroup_id()));
		}
		return result;
	}
	@Override
	public void putJoin(String user_id, int group_id) {
		// 멤버쉽 아이디
		int membership_id = 0;

		// 참가하는 중이면
		if (isJoin(user_id, group_id)) {
			List<OffUserMembership> joinlist = offUserMemberShipDao.selectMembershipByUserId(user_id);
			for (int i = 0; i < joinlist.size(); i++) {
				OffUserMembership join = joinlist.get(i);
				if (join.getGroup_id() == group_id) {
					membership_id = join.getMembership_id();
					break;
				}
			}
			// join 삭제
			offUserMemberShipDao.deleteMembership(membership_id);
			// 그룹에 참여자 수 업데이트
			OffGroup offgroup = offGroupDao.selectOneGroup(group_id);
			offgroup.setGroup_cnt(offgroup.getGroup_cnt() - 1);
			offGroupDao.updateGroup(offgroup);
		}
		// 참가하는 중이 아니면
		else {
			// join 생성
			OffUserMembership membership = new OffUserMembership();
			membership.setGroup_id(group_id);
			membership.setUser_id(user_id);
			offUserMemberShipDao.insertMembership(membership);
			// 그룹에 참여자 수 업데이트
			OffGroup offgroup = offGroupDao.selectOneGroup(group_id);
			offgroup.setGroup_cnt(offgroup.getGroup_cnt() + 1);
			offGroupDao.updateGroup(offgroup);
		}
	}

	@Override
	public int countJoin(OffGroup offGroup) {
		return offUserMemberShipDao.selectMembershipByGroupId(offGroup.getGroup_id()).size();
	}
}
