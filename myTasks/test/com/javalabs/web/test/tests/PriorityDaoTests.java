package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

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

import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PriorityDaoTests {

	private static Logger logger = Logger.getLogger(PriorityDaoTests.class);

	@Autowired
	private PriorityDao priorityDao;

	@Autowired
	private DataSource dataSource;

	private Priority priority1 = new Priority("low");
	private Priority priority2 = new Priority("medium");
	private Priority priority3 = new Priority("hight");
	private Priority priority4 = new Priority("urgent");
	private Priority priority5 = new Priority("+urgent");
	private Priority priority6 = new Priority("++urgent");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init PriorityDaoTests...");

		jdbc.execute("delete from t_task");
		jdbc.execute("delete from a_taskcategory");
		jdbc.execute("delete from a_taskPriority");
	}

	@Test
	public void testCreateRetrieve() {
		priorityDao.saveOrUpdate(priority1);
		priorityDao.saveOrUpdate(priority2);
		priorityDao.saveOrUpdate(priority3);
		priorityDao.saveOrUpdate(priority4);

		List<Priority> priorities = priorityDao.getAllPriorities();
		assertEquals("Should be 4 priorities.", 4, priorities.size());

		Priority priorityR1 = priorityDao.get(priority1.getIdTaskPriority());

		assertEquals("Retrieved priority should equal inserted priority.",
				priorityR1, priorities.get(0));

		priorityDao.saveOrUpdate(priority5);
		priorityDao.saveOrUpdate(priority6);

		List<Priority> priorities2 = priorityDao.getAllPriorities();
		assertEquals("Should be six prioritys.", 6, priorities2.size());
	}

	@Test
	public void testGetById() {
		priorityDao.saveOrUpdate(priority1);
		priorityDao.saveOrUpdate(priority2);
		priorityDao.saveOrUpdate(priority3);
		priorityDao.saveOrUpdate(priority4);

		Priority priorityR1 = priorityDao.get(priority1.getIdTaskPriority());
		assertEquals("prioritys should match", priority1, priorityR1);
	}

	@Test
	public void testUpdate() {
		priorityDao.saveOrUpdate(priority1);
		priorityDao.saveOrUpdate(priority2);

		priority2.setPriorityname("modifiedpriority");
		priorityDao.saveOrUpdate(priority2);

		Priority retrieved = priorityDao.get(priority2.getIdTaskPriority());
		assertEquals("Retrieved priority should be updated.", priority2,
				retrieved);
	}

	@Test
	public void testGetPriorityname() {
		priorityDao.saveOrUpdate(priority1);
		priorityDao.saveOrUpdate(priority2);

		Priority priorityR1 = priorityDao.get(priority2.getPriorityname());
		assertNotNull("Should be one priority.", priorityR1);

		Priority priorityR2 = priorityDao.get("abcd");
		assertNull("Should be zeri prioritys.", priorityR2);
	}

	@Test
	public void testDelete() {
		priorityDao.saveOrUpdate(priority1);
		priorityDao.saveOrUpdate(priority2);

		Priority priorityR1 = priorityDao.get(priority1.getIdTaskPriority());
		assertNotNull("Priority with ID " + priority1.getIdTaskPriority()
				+ " should not be null (deleted, actual)", priorityR1);

		priorityDao.delete(priority1.getIdTaskPriority());

		Priority priorityR2 = priorityDao.get(priorityR1.getIdTaskPriority());
		assertNull("Priority with ID " + priorityR1.getIdTaskPriority()
				+ " should be null (deleted, actual)", priorityR2);
	}
}
