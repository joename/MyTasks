package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("roleDao")
public class RoleDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Session session(){
	  return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public boolean create(Role role) {
		System.out.println(">RoleDao create " + role);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				role);
		String sql = "INSERT INTO t_role (rolename)"
				+ " VALUES (:rolename)";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public int[] create(List<Role> roles) {
		String sql = "INSERT INTO t_role (rolename)"
				+ " VALUES (:rolename)";

		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(roles.toArray());

		return jdbc.batchUpdate(sql, params);
	}

	@Transactional
	public boolean update(Role role) {
		System.out.println(">RoleDao update " + role);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				role);

		String sql = " UPDATE t_role SET rolename=:rolename, timestamp=:timestamp"
				+ " WHERE idRole=:idRole";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public boolean delete(long idRole) {
		System.out.println(">RoleDao delete " + idRole);
		MapSqlParameterSource params = new MapSqlParameterSource(
				"idRole", idRole);

		return jdbc
				.update("delete from t_role where idRole=:idRole",
						params) == 1;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		return session().createQuery("from Role").list();
		    //jdbc.query("select * from t_role",				new RoleRowMapper());
	}

	public Role getRole(long idRole) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idRole", idRole);

		String sql = "select * from t_role where idRole=:idRole";
		try {
			return jdbc.queryForObject(sql, params, new RoleRowMapper());
		// if the query does not return exactly one row
		} catch (IncorrectResultSizeDataAccessException ex) {
			return new Role(0);
		}
		// if the query fails
		catch (DataAccessException ex) {
			return new Role(0);
		}
	}
	
	public Role get(String rolename) {
		String sql = "select * from t_role where role=:role";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("role", rolename);

		return jdbc.queryForObject(sql, params, new RoleRowMapper());
	}

}
