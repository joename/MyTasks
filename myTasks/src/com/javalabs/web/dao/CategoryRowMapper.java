package com.javalabs.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CategoryRowMapper implements RowMapper<Category> {
	
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();

		category.setIdTaskCategory(rs.getLong("idTaskCategory"));
		category.setCategory(rs.getString("category"));
		category.setSortOrder(rs.getInt("sortOrder"));
		category.setTimestamp(rs.getDate("timestamp"));

		return category;
	}

}
