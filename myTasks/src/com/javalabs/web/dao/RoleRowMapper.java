package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role> {
	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();

		role.setIdRole(rs.getLong("idRole"));
		role.setRolename(rs.getString("rolename"));
		role.setTimestamp(rs.getDate("timestamp"));

		return role;
	}

}
