package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("categoryDao")
public class CategoryDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean create(Category category) {
		System.out.println(">CategoryDao create " + category);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				category);
		String sql = "INSERT INTO a_taskCategory (sortOrder, category)"
				+ " VALUES (:sortOrder, :category)";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public int[] create(List<Category> categories) {
		String sql = "INSERT INTO a_taskCategory (sortOrder, category)"
				+ " VALUES (:sortOrder, :category)";

		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(categories.toArray());

		return jdbc.batchUpdate(sql, params);
	}

	@Transactional
	public boolean update(Category category) {
		System.out.println(">CategoryDao update " + category);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				category);

		String sql = " UPDATE a_taskCategory SET sortOrder=:sortOrder, category=:category"
				+ " WHERE idTaskCategory=:idTaskCategory";

		return jdbc.update(sql, params) == 1;
	}

	@Transactional
	public boolean delete(long idTaskCategory) {
		System.out.println(">CategoryDao delete " + idTaskCategory);
		MapSqlParameterSource params = new MapSqlParameterSource(
				"idTaskCategory", idTaskCategory);

		return jdbc
				.update("delete from a_taskCategory where idTaskCategory=:idTaskCategory",
						params) == 1;
	}

	public List<Category> getCategories() {
		return jdbc.query("select * from a_taskCategory",
				new RowMapper<Category>() {

					public Category mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Category category = new Category();

						category.setIdTaskCategory(rs.getLong("idTaskCategory"));
						category.setSortOrder(rs.getInt("sortOrder"));
						category.setCategory(rs.getString("category"));
						category.setTimestamp(rs.getDate("timestamp"));
						return category;
					}
				});
	}

	public Category getCategory(long idTaskCategory) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("idTaskCategory", idTaskCategory);

		String sql = "select * from a_taskCategory where idTaskCategory=:idTaskCategory";
		try {
			return jdbc.queryForObject(sql, params, new CategoryRowMapper());
		// if the query does not return exactly one row
		} catch (IncorrectResultSizeDataAccessException ex) {
			return new Category(0);
		}
		// if the query fails
		catch (DataAccessException ex) {
			return new Category(0);
		}
	}
}
