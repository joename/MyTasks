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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("taskActionDao")
public class TaskActionDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public boolean create(TaskAction ta) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ta);
		String sql = "INSERT INTO t_taskaction (date, action, description, duration, idUser)"
				+ " VALUES (:date, :action, :description, :duration, :idUser)";

		return jdbc.update(sql, params) == 1;
	}

	public boolean update(TaskAction ta) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ta);

		String sql = " UPDATE t_taskAction"
				+ " SET date=:date, action=:action, description=:description, duration=:duration, idUser=:idUser";

		return jdbc.update(sql, params) == 1;
	}

	public boolean delete(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource(
				"idTaskAction", id);

		return jdbc.update(
				"delete from t_taskAction where idTaskAction=:idTaskAction", params) == 1;
	}

	public List<TaskAction> getTaskActions() {
		String sql = "select * from t_taskAction";
		return jdbc.query(sql, new RowMapper<TaskAction>() {

			public TaskAction mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				TaskAction ta = new TaskAction();

				ta.setIdTaskAction(rs.getLong("idTaskAction"));
				ta.setIdTask(rs.getLong("idTask"));
				ta.setDate(rs.getDate("date"));
				ta.setAction(rs.getString("action"));
				ta.setDescription(rs.getString("description"));
				ta.setDuration(rs.getInt("duration"));
				ta.setIdUser(rs.getLong("idUser"));
				ta.setTimestamp(rs.getDate("timestamp"));

				return ta;
			}
		});
	}

	@Transactional
	public List<Integer> create(List<TaskAction> tas) {

		List<Integer> result = new ArrayList<Integer>();

		for (TaskAction ta : tas) {
			if (this.create(ta)) {
				result.add(1);
			} else {
				result.add(0);
			}
		}
		return result;
	}
}