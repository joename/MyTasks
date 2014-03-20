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
import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskActionDaoTests {

	private static Logger logger = Logger.getLogger(TaskActionDaoTests.class);

	@Autowired
	private TaskActionDao taskActionDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init CategoryDaoTests...");
		jdbc.execute("delete from a_taskAction");
		// jdbc.execute("");
	}

	@Test
	public void testCategoryCreate() {
		TaskAction ta = new TaskAction();
		assertTrue("TaskAction creation should return true",
				taskActionDao.create(ta));
	}
	@Test
	public void testCategoryBatchCreate() {
		Category category1 = new Category("deutsch");
		Category category2 = new Category("englisch");
		Category category3 = new Category("spanisch");
		Category category4 = new Category("catalanisch");

		List<Category> categories = new ArrayList<Category>();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);
		categories.add(category4);
		
		int rvals[]= categoryDao.create(categories);

		int counter=0;
		for(int value:rvals){
			System.out.println("Updated " + value + "row");
			counter++;
		}
		assertTrue("State batch creation should return true",
				counter==4);
	}
	@Test
	public void testCategoryUpdate() {
		Category category1 = new Category("deutsch");
		
		categoryDao.create(category1);
		
		List<Category> categories = categoryDao.getCategories();
		Category category = categories.get(0);
		
		category.setCategory("mallorquinisch");
		
		assertTrue("Category update should return true",
				categoryDao.update(category));
	}

	@Test
	public void testCategoryDelete() {
		Category category1 = new Category("deutsch");
		
		categoryDao.create(category1);
		
		List<Category> categories = categoryDao.getCategories();
		Category category = categories.get(0);
		
		assertTrue("Category deletion should return true",
				categoryDao.delete(category.getIdTaskCategory()));
	}
}
