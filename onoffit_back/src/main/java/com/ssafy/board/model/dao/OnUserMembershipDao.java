package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.OnUserMembership;

public interface OnUserMembershipDao {
	// 유저 아이디로 참여 조회
	public List<OnUserMembership> selectMembershipByUserId(String user_id);

	// 전체 참여 조회
	public List<OnUserMembership> selectAllMembership();
	
	// 특정 그룹 참여 조회
	public List<OnUserMembership> selectMembershipByGroupId(int group_id);

	// 그룹참여 생성
	public void insertMembership(OnUserMembership membership);

	// 그룹참여 제거
	public void deleteMembership(int membership_id);
}
