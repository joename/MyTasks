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

@Component("priorityDao")
public class PriorityDao {

  private NamedParameterJdbcTemplate jdbc;

  @Autowired
  public void setDataSource(DataSource jdbc) {
    this.jdbc = new NamedParameterJdbcTemplate(jdbc);
  }

  public boolean create(Priority priority) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(priority);
    String sql = "INSERT INTO a_taskPriority (priority)" + " VALUES (:priority)";

    return jdbc.update(sql, params) == 1;
  }

  public boolean update(Priority priority) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(priority);

    String sql = " UPDATE a_taskPriority SET sortOrder=:sortOrder, aka=:aka, priority=:priority"
        + " WHERE idTaskPriority=:idTaskPriority";

    return jdbc.update(sql, params) == 1;
  }

  public boolean delete(long idTaskPriority) {
    MapSqlParameterSource params = new MapSqlParameterSource("idTaskPriority",
        idTaskPriority);

    String sql = "DELETE FROM a_taskPriority WHERE idTaskPriority=:idTaskPriority";

    return jdbc.update(sql, params) == 1;
  }

  public boolean delete(Priority priority) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(priority);

    String sql = "DELETE FROM a_taskPriority WHERE idTaskPriority=:idTaskPriority";

    return jdbc.update(sql, params) == 1;
  }

  @Transactional
  public int[] create(List<Priority> priorities) {

    String sql = "INSERT INTO a_taskPriority (priority) VALUES (:priority)";

    SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(priorities
        .toArray());
    return jdbc.batchUpdate(sql, params);
  }

  public List<Priority> getAllPriorities() {
    return jdbc.query("SELECT * FROM a_taskPriority", new PriorityRowMapper());
  }

  public Priority get(String priority) {
    String sql = "select * from a_taskPriority where priority=:priority";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("priority", priority);

    return jdbc.queryForObject(sql, params, new PriorityRowMapper());
  }
}
