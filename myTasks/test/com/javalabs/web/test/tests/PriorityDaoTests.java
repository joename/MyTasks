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

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init PriorityDaoTests...");
		jdbc.execute("delete from a_taskPriority");
		// jdbc.execute("");
	}

	@Test
	public void testPriorityCreate() {
		Priority priority = new Priority("low");
		logger.info("Create Priority..." + priority);
		assertTrue("Priority creation should return true",
				priorityDao.create(priority));
	}

	@Test
	public void testPriorityBatchCreate() {
		Priority priority1 = new Priority("low");
		Priority priority2 = new Priority("medium");
		Priority priority3 = new Priority("hight");
		Priority priority4 = new Priority("urgent");

		List<Priority> priorities = new ArrayList<Priority>();
		priorities.add(priority1);
		priorities.add(priority2);
		priorities.add(priority3);
		priorities.add(priority4);
		
		int rvals[]=priorityDao.create(priorities);

		int counter=0;
		for(int value:rvals){
			System.out.println("Updated " + value + "row");
			counter++;
		}
		assertTrue("Priority batch creation should return true",
				counter==4);
	}
	
	@Test
	public void testPriorityUpdate() {
		Priority priority1 = new Priority("low");
		Priority priority2 = new Priority("medium");
		Priority priority3 = new Priority("hight");
		Priority priority4 = new Priority("urgent");

		priorityDao.create(priority1);
		priorityDao.create(priority2);
		priorityDao.create(priority3);
		priorityDao.create(priority4);

		List<Priority> priorities = priorityDao.getPriorities();
		Priority priority = priorities.get(1);

		priority.setPriority("superurgent");
		
		priorityDao.update(priority);

		assertTrue("Priority update should return true",
				priorityDao.update(priority));
	}

	@Test
	public void testPriorityDelete() {
		Priority priority1 = new Priority("lowest");

		priorityDao.create(priority1);

		List<Priority> priorities = priorityDao.getPriorities();
		Priority priority = priorities.get(0);

		assertTrue("Priority deletion should return true",
				priorityDao.delete(priority.getIdTaskPriority()));
	}

}
