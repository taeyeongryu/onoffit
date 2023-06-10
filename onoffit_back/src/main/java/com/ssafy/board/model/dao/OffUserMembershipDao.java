package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.OffUserMembership;

public interface OffUserMembershipDao {
	// 유저 아이디로 참여 조회
	public List<OffUserMembership> selectMembershipByUserId(String user_id);

	// 전체 참여 조회
	public List<OffUserMembership> selectAllMembership();

	// 특정 그룹 참여 조회
	public List<OffUserMembership> selectMembershipByGroupId(int group_id);

	// 그룹참여 생성
	public void insertMembership(OffUserMembership membership);

	// 그룹참여 제거
	public void deleteMembership(int membership_id);

}
