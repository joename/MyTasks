package com.javalabs.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("taskActionDao")
public class TaskActionDao {

  private NamedParameterJdbcTemplate jdbc;

  @Autowired
  public void setDataSource(DataSource jdbc) {
    this.jdbc = new NamedParameterJdbcTemplate(jdbc);
  }

  @Transactional
  public boolean create(TaskAction ta) {

    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(ta);
    String sql = "INSERT INTO t_taskaction (idTask, date, action, description, duration, idUser)"
        + " VALUES (:idTask, :date, :action, :description, :duration, :idUser)";

    return jdbc.update(sql, params) == 1;
  }

  @Transactional
  public boolean update(TaskAction ta) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(ta);

    String sql = " UPDATE t_taskaction"
        + " SET date=:date, action=:action, description=:description, duration=:duration, idUser=:idUser"
        + " WHERE idTaskAction=:idTaskAction";

    return jdbc.update(sql, params) == 1;
  }

  @Transactional
  public boolean delete(long id) {
    MapSqlParameterSource params = new MapSqlParameterSource("idTaskAction", id);

    return jdbc.update("delete from t_taskaction where idTaskAction=:idTaskAction",
                       params) == 1;
  }

  public List<TaskAction> getTaskActions() {
    String sql = "select * from t_taskaction";
    return jdbc.query(sql, new TaskActionRowMapper());
  }

  @Transactional
  public int[] create(List<TaskAction> tas) {

    SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(tas.toArray());
    String sql = "INSERT INTO t_taskaction (idTask, date, action, description, duration, idUser)"
        + " VALUES (:idTask, :date, :action, :description, :duration, :idUser)";
    return jdbc.batchUpdate(sql, params);
  }
}