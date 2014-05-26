package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserDao;

@Service("userService")
public class UserService {

	private UserDao userDao;

	@Autowired
	private void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User get(String username){
		return userDao.get(username);
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	public boolean exists(String username) {
		return userDao.exists(username);
	}
}
