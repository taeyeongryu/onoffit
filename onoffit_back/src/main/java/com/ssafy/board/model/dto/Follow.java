package com.ssafy.board.model.dto;

public class Follow {
	// 변수 생성
	private int follow_id;
	private String follower_id;
	private String following_id;

	// 기본 생성자
	public Follow() {
	}

	// 매개변수 포함된 생성자
	public Follow(int follow_id, String follower_id, String following_id) {
		this.follow_id = follow_id;
		this.follower_id = follower_id;
		this.following_id = following_id;
	}

	// getter & setter
	public int getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}

	public String getFollower_id() {
		return follower_id;
	}

	public void setFollower_id(String follower_id) {
		this.follower_id = follower_id;
	}

	public String getFollowing_id() {
		return following_id;
	}

	public void setFollowing_id(String following_id) {
		this.following_id = following_id;
	}

	// toString()
	@Override
	public String toString() {
		return "Follow [follow_id=" + follow_id + ", follower_id=" + follower_id + ", following_id=" + following_id
				+ "]";
	}

}
