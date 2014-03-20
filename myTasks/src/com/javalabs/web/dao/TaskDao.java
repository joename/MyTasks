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

@Component("taskDao")
public class TaskDao {

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }
    
    @Transactional
    public boolean create(Task task) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                task);
        String sql = "INSERT INTO t_task (taskname, description, pending, date, deadline, idTaskCategory, idTaskPriority, idTaskState, idUser, idUser_responsible, evaluation, timestamp)"
                + " VALUES (:taskname, :description, :pending, :date, :deadline, :idCategory, :idPriority, :idState, :idUser, :idUserResponsible, :evaluation, :timestamp)";

        return jdbc.update(sql, params) == 1;
    }

    public boolean update(Task task) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                task);

        String sql = " UPDATE INTO t_task (idTask, taskname, description, pending, date, deadline, idTaskCategory, idTaskPriority, idTaskState, idUser, idUser_responsible, evaluation, timestamp)"
                + " VALUES (:id, :taskname, :description, :pending, :date, :deadline, :idCategory, :idPriority, :idState, :idUser, :idUserResponsible, :evaluation, :timestamp)";

        return jdbc.update(sql, params) == 1;
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("idTask", id);

        return jdbc.update("delete from t_task where idTask=:idTask", params) == 1;
    }

    public List<Task> getTasks() {
        return jdbc.query("select * from t_task", new RowMapper<Task>() {

            public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
                Task task = new Task();

                task.setId(rs.getLong("idTask"));
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
        });
    }

    @Transactional
    public List<Integer> create(List<Task> tasks) {
        /*
         * SqlParameterSource[] params = SqlParameterSourceUtils
         * .createBatch(tasks.toArray()); String sql =
         * "INSERT INTO t_task (taskname, description, pending, date, deadline, idTaskCategory, idTaskPriority, idTaskState, idUser, idUser_responsible, evaluation, timestamp)"
         * +
         * " VALUES (:taskname, :description, :pending, :date, :deadline, :idTaskCategory, :idTaskPriority, :idTaskState, :idUser, :idUser_responsible, :evaluation, :timestamp)"
         * ; return jdbc.batchUpdate(sql, params);
         */
        List<Integer> result = new ArrayList<>();

        for (Task task : tasks) {
            if (this.create(task)) {
                result.add(1);
            } else {
                result.add(0);
            }

        }
        return result;
    }

    public Task getTask(long id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from t_task where idTask=:id", params,
                new RowMapper<Task>() {

                    public Task mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Task task = new Task();

                        task.setId(rs.getLong("idTask"));
                        task.setTaskname(rs.getString("taskname"));
                        task.setDescription(rs.getString("description"));
                        task.setDate(rs.getDate("date"));
                        task.setIdCategory(rs.getLong("idTaskCategory"));
                        task.setIdPriority(rs.getLong("idTaskPriority"));
                        task.setIdState(rs.getLong("idTaskState"));
                        task.setIdUser(rs.getLong("idUser"));
                        task.setIdUserResponsible(rs
                                .getLong("idUser_responsible"));
                        task.setEvaluation(rs.getString("evaluation"));
                        task.setPending(rs.getInt("pending"));
                        task.setTimestamp(rs.getDate("timestamp"));

                        return task;
                    }

                });
    }
}