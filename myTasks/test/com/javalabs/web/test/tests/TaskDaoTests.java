package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryDao;
import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityDao;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskDao;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StateDao;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskDaoTests {

	private static Logger logger = Logger.getLogger(TaskDaoTests.class);

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private TaskDao taskTaskDao;
	@Autowired
	private PriorityDao taskPriorityDao;
	@Autowired
	private CategoryDao taskCategoryDao;
	@Autowired
	private StateDao taskStateDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private DataSource dataSource;

	private Category category = new Category("deutsch");
	private Priority priority = new Priority("low");
	private State state = new State("pending");
	private User user = new User("jose", "jose", "jose@javalabs.com", true,
			"ROLE_USER", "joe");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init TaskDaoTests...");
		jdbc.execute("delete from t_task");
		jdbc.execute("delete from t_taskaction");
		jdbc.execute("delete from t_user");
		jdbc.execute("delete from a_taskcategory");
		jdbc.execute("delete from a_taskpriority");
		jdbc.execute("delete from a_taskstate");
	}

	@Test
	public void testCreateRetrieve() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);
		
		java.util.Date date = new java.util.Date();
		java.sql.Date datesql = new java.sql.Date(date.getTime());
		java.sql.Timestamp stamp = new java.sql.Timestamp(date.getTime());
		Date rightnow = stamp;//Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t2 = new Task("My second task", "This is a task", date,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t3 = new Task("My third task", "This is a task", datesql,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t4 = new Task("My sixth task", "This is a task", rightnow,
				category, priority, user);

		taskDao.saveOrUpdate(t1);
		taskDao.saveOrUpdate(t2);
		taskDao.saveOrUpdate(t3);
		taskDao.saveOrUpdate(t4);

		List<Task> tasks = taskDao.getAllTasks();
		assertEquals("Should be 4 tasks.", 4, tasks.size());

		Task taskR1 = taskDao.get(t1.getIdTask());
		Task taskR2 = taskDao.get(t2.getIdTask());
		Task taskR3 = taskDao.get(t3.getIdTask());
		
		System.out.println("Date obj     >"+((rightnow.getTime()))
				+" - Timest obj>"+((stamp.getTime())));
		//System.out.println("BD>>>"+((t1.getDate().getTime()))
		//		+"T***"+((t1.getDeadline().getTime())));
		System.out.println("After recover>"+((taskR1.getDate().getTime()))
				+" - After TS  >"+((taskR1.getDate().getTime())));
		System.out.println("Date before/ >"+((t1.getDeadline().getTime()/1000)*1000)
				+" - Before TS/>"+((t1.getDeadline().getTime()/1000)*1000));
		System.out.println("Date after / >"+((taskR1.getDate().getTime()/1000)*1000)
				+" - After TS/ >"+((taskR1.getDeadline().getTime()/1000)*1000));
		
		System.out.println("l timestamp> "+rightnow.getTime());
		System.out.println("l date     >"+date.getTime());
		System.out.println("l datesql  >"+datesql.getTime());
		System.out.println("l tr1date  >"+taskR1.getDate().getTime());
		System.out.println("l tr2date  >"+taskR2.getDate().getTime());
		System.out.println("l tr3date  >"+taskR3.getDate().getTime());
		
		System.out.println("rightnow "+rightnow);
		System.out.println("date     "+date);
		System.out.println("date sql "+datesql);
		System.out.println("t1 date  "+t1.getDate());
		System.out.println("tR1 date "+taskR1.getDate());
		System.out.println("t2 date  "+t2.getDate());
		System.out.println("tR2 date "+taskR2.getDate());
		System.out.println("t3 date  "+t2.getDate());
		System.out.println("tR3 date "+taskR2.getDate());
		//System.out.println((Date)t1.getDate());
		//System.out.println((Date)taskR1.getDate());
		
		System.out.println("t3.getDate().compareTo(taskR3.getDate()) == 0 :" + (t3.getDate().compareTo(taskR3.getDate()) == 0));
		System.out.println("t3.getDate().equal(taskR3.getDate()) == 0 :" + (t3.getDate().compareTo(taskR3.getDate()) == 0));
		System.out.println("taskR3.getDate().compareTo(t3.getDate()) == 0 :" + (t3.getDate().compareTo(taskR3.getDate()) == 0));
		System.out.println("taskR3.getDate().equal(t3.getDate()) == 0 :" + (t3.getDate().compareTo(taskR3.getDate()) == 0));
		
		assertTrue("Task dates must be equal",
				t1.getDate().compareTo(taskR1.getDate()) == 0);
		assertEquals("Retrieved task should be same hashCode inserted task.", taskR1.hashCode(),
				t1.hashCode());
	}

	@Test
	public void testGetById() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();
		Timestamp stamp = new Timestamp(rightnow.getTime());

		Task t1 = new Task("My first task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);

		Task taskR1 = taskDao.get(t1.getIdTask());
		assertEquals("tasks should match", t1, taskR1);
	}

	@Test
	public void testUpdate() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();
		Timestamp stamp = new Timestamp(rightnow.getTime());

		Task t1 = new Task("My first task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);
		taskDao.saveOrUpdate(t2);

		t2.setTaskname("modifiedtask");
		taskDao.saveOrUpdate(t2);

		Task retrieved = taskDao.get(t2.getIdTask());
		assertEquals("Retrieved task should be updated.", t2, retrieved);
	}

	@Test
	public void testGetTaskname() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();
		Timestamp stamp = new Timestamp(rightnow.getTime());

		Task t1 = new Task("My first task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);
		taskDao.saveOrUpdate(t2);

		Task taskR1 = taskDao.get(t2.getTaskname());
		assertNotNull("Should be one task.", taskR1);

		Task taskR2 = taskDao.get("abcd");
		assertNull("Should be zeri tasks.", taskR2);
	}

	@Test
	public void testDelete() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		Date rightnow = Calendar.getInstance().getTime();
		Timestamp stamp = new Timestamp(rightnow.getTime());

		Task t1 = new Task("My first task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				stamp, category, priority, state, user, user, "okey", 0);

		taskDao.saveOrUpdate(t1);
		taskDao.saveOrUpdate(t2);

		Task taskR1 = taskDao.get(t1.getIdTask());
		assertNotNull("Task with ID " + t1.getIdTask()
				+ " should not be null (deleted, actual)", taskR1);

		taskDao.delete(t1.getIdTask());

		Task taskR2 = taskDao.get(taskR1.getIdTask());
		assertNull("Task with ID " + taskR1.getIdTask()
				+ " should be null (deleted, actual)", taskR2);
	}

	/*@Test
	public void testTimestampVsDate() {
		java.util.Date date = new java.util.Date();
		java.util.Date stamp = new java.sql.Timestamp(date.getTime());
		assertTrue("date.equals(stamp)", date.equals(stamp));
		assertTrue("stamp.compareTo(date)", stamp.compareTo(date) == 0);
		assertTrue("date.compareTo(stamp)", date.compareTo(stamp) == 0);
		assertTrue("stamp.equals(date)", stamp.equals(date));
	}

	@Test
	public void testTimestampVsDate1() {
		java.util.Date date = new java.util.Date();
		java.util.Date stamp = new java.sql.Timestamp(date.getTime());
		assertTrue("date.equals(stamp)", date.equals(stamp));
	}

	@Test
	public void testTimestampVsDate2() {
		java.util.Date date = new java.util.Date();
		java.util.Date stamp = new java.sql.Timestamp(date.getTime());
		assertTrue("stamp.compareTo(date)", stamp.compareTo(date) == 0);
	}

	@Test
	public void testTimestampVsDate3() {
		java.util.Date date = new java.util.Date();
		java.util.Date stamp = new java.sql.Timestamp(date.getTime());
		assertTrue("date.compareTo(stamp)", date.compareTo(stamp) == 0);
	}

	@Test
	public void testTimestampVsDate4() {
		java.util.Date date = new java.util.Date();
		java.util.Date stamp = new java.sql.Timestamp(date.getTime());
		assertTrue("stamp.equals(date)", stamp.equals(date));
	}*/
}
