package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setIdTask(rs.getLong("idTask"));
        task.setTaskname(rs.getString("taskname"));
        task.setDescription(rs.getString("description"));
        task.setDate(rs.getDate("date"));
        task.setIdCategory(rs.getLong("idTaskCategory"));
        task.setIdPriority(rs.getLong("idTaskPriority"));
        task.setIdState(rs.getLong("idTaskState"));
        task.setIdUser(rs.getLong("idUser"));
        task.setIdUserResponsible(rs.getLong("idUser_responsible"));
        task.setEvaluation(rs.getString("evaluation"));
        task.setPending(rs.getInt("pending"));
        task.setTimestamp(rs.getDate("timestamp"));

        return task;
    }
}
