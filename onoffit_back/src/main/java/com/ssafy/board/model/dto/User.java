package com.ssafy.board.model.dto;

public class User {
	// 변수 생성
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_nickname;
	private String user_email;

	// 기본 생성자
	public User() {

	}

	// 매개변수 포함된 생성자
	public User(String user_id, String user_password, String user_name, String user_nickname, String user_email) {
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_nickname = user_nickname;
		this.user_email = user_email;
	}

	// getter & setter
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	// toString()
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_password=" + user_password + ", user_name=" + user_name
				+ ", user_nickname=" + user_nickname + ", user_email=" + user_email + "]";
	}
}
