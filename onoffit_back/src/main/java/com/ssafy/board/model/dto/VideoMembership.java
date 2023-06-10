package com.ssafy.board.model.dto;

public class VideoMembership {
	// 변수 생성
	private int membership_id;
	private String video_id;
	private int group_id;

	// 기본 생성자
	public VideoMembership() {
	}

	// 매개변수 포함된 생성자
	public VideoMembership(String video_id, int group_id, int membership_id) {
		super();
		this.video_id = video_id;
		this.group_id = group_id;
		this.membership_id = membership_id;
	}

	// getter & setter
	public String getVideo_id() {
		return video_id;
	}

	

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

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

	// toString()
	@Override
	public String toString() {
		return "Video_membership [video_id=" + video_id + ", group_id=" + group_id + ", membership_id=" + membership_id
				+ "]";
	}

}
