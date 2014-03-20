package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StateRowMapper implements RowMapper<State> {

	@Override
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		State state = new State();

		state.setIdTaskState(rs.getLong("idTaskState"));
		state.setSortOrder(rs.getInt("sortOrder"));
		state.setState(rs.getString("state"));
		state.setTimestamp(rs.getDate("timestamp"));

		return state;
	}

}
