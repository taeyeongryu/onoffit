package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.OnGroup;

public interface OnGroupDao {
	// 전체 그룹 조회하기
	public List<OnGroup> selectAll();

	// 그룹 하나 조회하기
	public OnGroup selectOneGroup(int group_id);

	// 그룹 삭제
	public void deleteGroup(int group_id);

	// 그룹 업데이트
	public void updateGroup(OnGroup group);

	// 그룹 생성
	public void insertGroup(OnGroup group);
}