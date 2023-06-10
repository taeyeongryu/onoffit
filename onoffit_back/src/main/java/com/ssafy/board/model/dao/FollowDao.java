package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Follow;

public interface FollowDao {
	//팔로우 조회하기
	public List<Follow> selectFollowList(String follower_id);
	//팔로우 하기
	public void insertFollow(Follow follow);
	//팔로우 끊기
	public void deleteFollow(int follow_id);
}
