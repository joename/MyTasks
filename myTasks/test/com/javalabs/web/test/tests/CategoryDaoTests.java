package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalabs.web.controllers.HomeController;
import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryDao;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTests {

	private static Logger logger = Logger.getLogger(CategoryDaoTests.class);

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private DataSource dataSource;

	private Category category1 = new Category("deutsch");
	private Category category2 = new Category("englisch");
	private Category category3 = new Category("spanisch");
	private Category category4 = new Category("catalanisch");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init CategoryDaoTests...");
		jdbc.execute("delete from t_task");
	    jdbc.execute("delete from a_taskcategory");
	}

	@Test
	public void testCreateRetrieve() {
		categoryDao.saveOrUpdate(category1);

	    List<Category> categories = categoryDao.getAllCategories();

	    assertEquals("One category should have been created and retrieved", 1, categories.size());
	    assertEquals("Inserted user should match category retreived", category1, categories.get(0));
	    
	    categoryDao.saveOrUpdate(category2);
	    categoryDao.saveOrUpdate(category3);
	    categoryDao.saveOrUpdate(category4);
	    
	    categories = categoryDao.getAllCategories();
	    
	    assertEquals("Should be 4 inserted users", 4, categories.size());
	}
	
	@Test
	public void testExists() {
		categoryDao.saveOrUpdate(category1);
		categoryDao.saveOrUpdate(category2);
		categoryDao.saveOrUpdate(category3);
		
		assertTrue("Category should exist.", categoryDao.exists(category2.getCategoryname()));
		assertFalse("Category should not exist.", categoryDao.exists("xkjhsfjlsjf"));
	}
}
