package com.ssafy.board.model.dto;

public class OnUserMembership {
	// 변수 생성
	private int group_id;
	private int membership_id;
	private String user_id;

	// 기본 생성자
	public OnUserMembership() {
	}

	// 매개변수 포함된 생성자
	public OnUserMembership(int group_id, int membership_id, String user_id) {
		this.group_id = group_id;
		this.membership_id = membership_id;
		this.user_id = user_id;
	}

	// getter & setter
	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(int membership_id) {
		this.membership_id = membership_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	// toString()
	@Override
	public String toString() {
		return "On_user_membership [group_id=" + group_id + ", membership_id=" + membership_id + ", user_id=" + user_id
				+ "]";
	}

}
