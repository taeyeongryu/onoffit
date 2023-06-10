package com.ssafy.board.model.dto;

public class OffGroup {
	// 변수 생성
	private int group_id;
	private String group_title;
	private String group_message;
	private String group_description;
	private int group_cnt;
	private String group_leader_user_id;
	private String group_address;
	private String group_image;
	private String group_orgimage;
	private boolean joined;
	
	public OffGroup() {}
	
	public OffGroup(int group_id, String group_title, String group_message, String group_description, int group_cnt,
			String group_leader_user_id, String group_address, String group_image, String group_orgimage,
			boolean joined) {
		super();
		this.group_id = group_id;
		this.group_title = group_title;
		this.group_message = group_message;
		this.group_description = group_description;
		this.group_cnt = group_cnt;
		this.group_leader_user_id = group_leader_user_id;
		this.group_address = group_address;
		this.group_image = group_image;
		this.group_orgimage = group_orgimage;
		this.joined = joined;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_title() {
		return group_title;
	}

	public void setGroup_title(String group_title) {
		this.group_title = group_title;
	}

	public String getGroup_message() {
		return group_message;
	}

	public void setGroup_message(String group_message) {
		this.group_message = group_message;
	}

	public String getGroup_description() {
		return group_description;
	}

	public void setGroup_description(String group_description) {
		this.group_description = group_description;
	}

	public int getGroup_cnt() {
		return group_cnt;
	}

	public void setGroup_cnt(int group_cnt) {
		this.group_cnt = group_cnt;
	}

	public String getGroup_leader_user_id() {
		return group_leader_user_id;
	}

	public void setGroup_leader_user_id(String group_leader_user_id) {
		this.group_leader_user_id = group_leader_user_id;
	}

	public String getGroup_address() {
		return group_address;
	}

	public void setGroup_address(String group_address) {
		this.group_address = group_address;
	}

	public String getGroup_image() {
		return group_image;
	}

	public void setGroup_image(String group_image) {
		this.group_image = group_image;
	}

	public String getGroup_orgimage() {
		return group_orgimage;
	}

	public void setGroup_orgimage(String group_orgimage) {
		this.group_orgimage = group_orgimage;
	}

	public boolean isJoined() {
		return joined;
	}

	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	@Override
	public String toString() {
		return "OffGroup [group_id=" + group_id + ", group_title=" + group_title + ", group_message=" + group_message
				+ ", group_description=" + group_description + ", group_cnt=" + group_cnt + ", group_leader_user_id="
				+ group_leader_user_id + ", group_address=" + group_address + ", group_image=" + group_image
				+ ", group_orgimage=" + group_orgimage + ", joined=" + joined + "]";
	}

}
