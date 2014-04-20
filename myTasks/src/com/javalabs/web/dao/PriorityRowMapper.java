package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PriorityRowMapper implements RowMapper<Priority> {

	@Override
	public Priority mapRow(ResultSet rs, int rowNum) throws SQLException {
		Priority priority = new Priority();

		priority.setIdTaskPriority(rs.getLong("idTaskPriority"));
		priority.setSortOrder(rs.getInt("sortOrder"));
		priority.setAka(rs.getString("aka"));
		priority.setPriorityname(rs.getString("priorityname"));
		priority.setTimestamp(rs.getDate("timestamp"));

		return priority;
	}
}
