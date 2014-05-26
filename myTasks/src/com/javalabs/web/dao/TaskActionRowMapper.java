package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskActionRowMapper implements RowMapper<TaskAction> {

	@Override
	public TaskAction mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		TaskAction ta = new TaskAction();

		ta.setIdTaskAction(rs.getLong("idTaskAction"));
		ta.setIdTask(rs.getLong("idTask"));
		ta.setDate(rs.getDate("date"));
		ta.setActionname(rs.getString("action"));
		ta.setDescription(rs.getString("description"));
		ta.setDuration(rs.getInt("duration"));
		ta.setIdUser(rs.getLong("idUser"));
		ta.setTimestamp(rs.getDate("timestamp"));

		return ta;
	}

}
