package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.User;

public interface UserDao {
	// c
	// 회원가입, 유저추가
	public void insertUser(User user);

	// r
	// 유저 조회
	public User selectOne(String user_id);

	// 유저 전체 조회
	public List<User> getUserList();

	// u
	// 유저 정보 수정
	public void updateUser(User user);

	// d
	// 유저 삭제
	public void deleteUser(String user_id);

}
