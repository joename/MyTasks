package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();

		user.setIdUser(rs.getLong("idUser"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setblockedAccess(rs.getBoolean("blockedAccess"));
		user.setAka(rs.getString("aka"));
		user.setPosition(rs.getString("position"));
		user.setPasswordDate(rs.getDate("passwordDate"));
		user.setLogInSession(rs.getDate("logInSession"));
		user.setLogOutSession(rs.getDate("logOutSession"));
		user.setComputerName(rs.getString("computerName"));
		user.setExtension(rs.getString("extension"));
		user.setTimestamp(rs.getDate("timestamp"));

		return user;
	}
}
