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

@Component("taskDao")
public class TaskDao {

  private NamedParameterJdbcTemplate jdbc;

  @Autowired
  public void setDataSource(DataSource jdbc) {
    this.jdbc = new NamedParameterJdbcTemplate(jdbc);
  }

  @Transactional
  public boolean create(Task task) {

    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(task);
    String sql = "INSERT INTO t_task (taskname, description, pending, date, deadline, idTaskCategory, idTaskPriority, idTaskState, idUser, idUser_responsible, evaluation, timestamp)"
        + " VALUES (:taskname, :description, :pending, :date, :deadline, :idCategory, :idPriority, :idState, :idUser, :idUserResponsible, :evaluation, :timestamp)";

    return jdbc.update(sql, params) == 1;
  }

  public boolean update(Task task) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(task);

    String sql = " UPDATE t_task"
        + " SET taskname=:taskname, description=:description, pending=:pending, date=:date, deadline=:deadline, idTaskCategory=:idCategory, idTaskPriority=:idPriority, idTaskState=:idState, idUser=:idUser, idUser_responsible=:idUserResponsible, evaluation=:evaluation, timestamp=:timestamp"
        + " WHERE idTask=:idTask";

    return jdbc.update(sql, params) == 1;
  }

  public boolean delete(long id) {
    MapSqlParameterSource params = new MapSqlParameterSource("idTask", id);

    return jdbc.update("delete from t_task where idTask=:idTask", params) == 1;
  }

  public List<Task> getTasks() {
    return jdbc.query("select * from t_task", new TaskRowMapper());
  }

  @Transactional
  public int[] create(List<Task> tasks) {

    SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(tasks.toArray());

    String sql = "INSERT INTO t_task (taskname, description, pending, date, deadline, idTaskCategory, idTaskPriority, idTaskState, idUser, idUser_responsible, evaluation, timestamp)"
        + " VALUES (:taskname, :description, :pending, :date, :deadline, :idCategory, :idPriority, :idState, :idUser, :idUserResponsible, :evaluation, :timestamp)";

    return jdbc.batchUpdate(sql, params);
  }

  public Task getTask(long id) {

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("id", id);

    return jdbc.queryForObject("select * from t_task where idTask=:id", params,
                               new TaskRowMapper());
  }
}