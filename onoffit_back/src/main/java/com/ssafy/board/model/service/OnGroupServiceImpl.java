package com.ssafy.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.OnGroupDao;
import com.ssafy.board.model.dao.OnUserMembershipDao;
import com.ssafy.board.model.dto.OnGroup;
import com.ssafy.board.model.dto.OnUserMembership;

@Service
public class OnGroupServiceImpl implements OnGroupService {
	@Autowired
	OnGroupDao onGroupDao;
	@Autowired
	OnUserMembershipDao onUserMemberShipDao;

	@Override
	public OnGroup registOnGroup(OnGroup onGroup) {
		onGroupDao.insertGroup(onGroup);
		OnUserMembership tmp = new OnUserMembership();
		tmp.setGroup_id(onGroup.getGroup_id());
		tmp.setUser_id(onGroup.getGroup_leader_user_id());
		onUserMemberShipDao.insertMembership(tmp);
		return onGroup;
	}

	@Override
	public void updateOnGroup(OnGroup onGroup) {
		onGroupDao.updateGroup(onGroup);
	}

	@Override
	public void deleteOnGroup(int group_id) {
		onGroupDao.deleteGroup(group_id);
	}

	@Override
	public List<OnGroup> selectOnGroupList() {
		return onGroupDao.selectAll();
	}

	@Override
	public boolean isJoin(String user_id, int group_id) {
		List<OnUserMembership> joinlist = onUserMemberShipDao.selectMembershipByUserId(user_id);
		for (int i = 0; i < joinlist.size(); i++) {
			OnUserMembership join = joinlist.get(i);
			if (join.getGroup_id() == group_id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void putJoin(String user_id, int group_id) {
		// 멤버쉽 아이디
		int membership_id = 0;

		// 참가하는 중이면
		if (isJoin(user_id, group_id)) {
			List<OnUserMembership> joinlist = onUserMemberShipDao.selectMembershipByUserId(user_id);
			for (int i = 0; i < joinlist.size(); i++) {
				OnUserMembership join = joinlist.get(i);
				if (join.getGroup_id() == group_id) {
					membership_id = join.getMembership_id();
					break;
				}
			}
			// join 삭제
			onUserMemberShipDao.deleteMembership(membership_id);
			// 그룹에 참여자 수 업데이트
			OnGroup ongroup = onGroupDao.selectOneGroup(group_id);
			ongroup.setGroup_cnt(ongroup.getGroup_cnt() - 1);
			onGroupDao.updateGroup(ongroup);
		}
		// 참가하는 중이 아니면
		else {
			// join 생성
			OnUserMembership membership = new OnUserMembership();
			membership.setGroup_id(group_id);
			membership.setUser_id(user_id);
			onUserMemberShipDao.insertMembership(membership);
			// 그룹에 참여자 수 업데이트
			OnGroup ongroup = onGroupDao.selectOneGroup(group_id);
			ongroup.setGroup_cnt(ongroup.getGroup_cnt() + 1);
			onGroupDao.updateGroup(ongroup);
		}

	}

	@Override
	public int countJoin(OnGroup onGroup) {
		return onUserMemberShipDao.selectMembershipByGroupId(onGroup.getGroup_id()).size();
	}

	@Override
	public List<OnGroup> getMyGroup(String user_id) {
		
		List<OnUserMembership> tmpList=onUserMemberShipDao.selectMembershipByUserId(user_id);
		System.out.println(tmpList);
		List<OnGroup> result=new ArrayList<OnGroup>();
		for(OnUserMembership us : tmpList) {
			System.out.println(onGroupDao.selectOneGroup(us.getGroup_id()));
			result.add(onGroupDao.selectOneGroup(us.getGroup_id()));
		}
		return result;
	}

}
