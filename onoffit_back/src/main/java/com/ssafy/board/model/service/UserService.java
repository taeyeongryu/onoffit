package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.User;

public interface UserService {
	//점검을 해야될 것 같긴 함 로그인을 어떻게 처리할지 확인해야됨
	void insertUser(User user);
	User selectOne(String id);
	void deleteUser(String id);
	void updateUser(User user);
	List<User> getUserList();
}
