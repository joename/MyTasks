package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDao")
public class UserDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				user);
		System.out.println(">>>>>>>>>" + user);
		String sql = "INSERT INTO t_user (username, password, email, blockedAccess, aka, position, extension, computerName)"
				+ " VALUES (:username, :password, :email, :blockedAccess, :aka, :position, :extension, :computerName)";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public int[] create(List<User> users) {
		String sql = "INSERT INTO t_user (username, password, email, blockedAccess, aka, position, extension, computerName)"
				+ " VALUES (:username, :password, :email, :blockedAccess, :aka, :position, :extension, :computerName)";

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(users
				.toArray());
		return jdbc.batchUpdate(sql, params);
	}

	@Transactional
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				user);

		String sql = " UPDATE t_user SET username=:username, password=:password, email=:email, blockedAccess=:blockedAccess, aka=:aka, position=:position, logInSession=:logInSession, logOutSession=:logOutSession, passwordDate=:passwordDate, extension=:extension, computerName=:computerName"
				+ " WHERE (t_user.idUser = :idUser)";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public boolean delete(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource("idUser", id);

		return jdbc.update("delete from t_user where idUser=:idUser", params) == 1;
	}

	public User get(long id) {
		String sql = "select * from t_user where idUser=:idUser";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idUser", id);

		return jdbc.queryForObject(sql, params, new UserRowMapper());
	}

	public User get(String userName) {
		String sql = "select * from t_user where userName=:userName";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userName", userName);

		return jdbc.queryForObject(sql, params, new UserRowMapper());
	}
	
	public List<User> getUsers() {
		return jdbc.query("select * from t_user", new UserRowMapper());
	}

	public boolean exists(String username) {
		String sql = "select count(idUser) from t_user where username=:username";
		return jdbc.queryForObject(sql, new MapSqlParameterSource("username",
				username), Integer.class) > 0;
	}
}