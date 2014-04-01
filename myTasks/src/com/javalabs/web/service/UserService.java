package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

	public void create(User user) {
		userDao.create(user);
	}
}
