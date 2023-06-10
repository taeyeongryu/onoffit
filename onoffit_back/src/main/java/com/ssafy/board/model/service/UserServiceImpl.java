package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);

	}

	@Override
	public User selectOne(String id) {
		return userDao.selectOne(id);
	}

	@Override
	public void deleteUser(String id) {
		userDao.deleteUser(id);

	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

}
