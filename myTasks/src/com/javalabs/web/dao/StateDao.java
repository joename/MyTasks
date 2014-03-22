package com.javalabs.web.dao;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("stateDao")
public class StateDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public State get(long id) {
		String sql = "select * from a_taskState where idTaskState=:id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject(sql, params, new StateRowMapper());
	}

	public State get(String stateName) {
		String sql = "select * from a_taskState where state=:state";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("state", stateName);

		return jdbc.queryForObject(sql, params, new StateRowMapper());
	}

	public boolean create(State state) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				state);
		String sql = "INSERT INTO a_taskstate (sortOrder, state)"
				+ " VALUES (:sortOrder, :state)";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public int[] create(List<State> states) {
		String sql = "INSERT INTO a_taskState (sortOrder, state)"
				+ " VALUES (:sortOrder, :state)";

		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(states.toArray());

		return jdbc.batchUpdate(sql, params);
	}

	public boolean update(State state) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				state);

		String sql = " UPDATE a_taskState"
				+ " SET sortOrder=:sortOrder, state=:state";

		return jdbc.update(sql, params) == 1;
	}

	public boolean delete(long id) {
		MapSqlParameterSource params = new MapSqlParameterSource("idTaskState",
				id);

		return jdbc.update(
				"delete from a_taskState where idTaskState=:idTaskState",
				params) == 1;
	}

	public List<State> getStates() {
		return jdbc.query("select * from a_taskState", new RowMapper<State>() {

			public State mapRow(ResultSet rs, int rowNum) throws SQLException {
				State state = new State(rs.getLong("idTaskState"));
				state.setSortOrder(rs.getInt("sortOrder"));
				state.setState(rs.getString("state"));
				state.setTimestamp(rs.getDate("timestamp"));
				return state;
			}
		});
	}
}
