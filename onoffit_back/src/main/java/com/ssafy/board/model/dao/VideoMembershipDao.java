package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.VideoMembership;

public interface VideoMembershipDao {
	// 그룹으로 비디오 조회
	public List<VideoMembership> selectAllMemberships(int group_id);

	// 멤버쉽 아이디로 비디오선택 조회
	public VideoMembership selectOneMembership(int membership_id);

	// 그룹에 비디오 추가
	public void insertMembership(VideoMembership membership);

	// 그룹에 비디오 제거
	public void deleteMembership(int membership_id);
}
