package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

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

		long idCategory = category.getIdTaskCategory();

		logger.info(">>>>idCategory" + idCategory);

		long idPriority = priority.getIdTaskPriority();
		long idState = state.getIdTaskState();
		long idUser = user.getIdUser();
		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t3 = new Task("My third task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t4 = new Task("My forth task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t5 = new Task("My fifth task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t6 = new Task("My sixth task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);

		taskDao.saveOrUpdate(t1);
		taskDao.saveOrUpdate(t2);
		taskDao.saveOrUpdate(t3);
		taskDao.saveOrUpdate(t4);

		List<Task> tasks = taskDao.getAllTasks();
		assertEquals("Should be 4 tasks.", 4, tasks.size());

		Task taskR1 = taskDao.get(t1.getIdTask());
		assertEquals("New comparation",new Date(taskR1.getDate().getTime()), t1.getDate());
assertEquals("They should have to be equal dates",taskR1.getDate().getTime(),t1.getDate());
		assertEquals("Retrieved task should be equal inserted task.", taskR1,
				t1);
		taskDao.saveOrUpdate(t5);
		taskDao.saveOrUpdate(t6);

		List<Task> tasks2 = taskDao.getAllTasks();
		assertEquals("Should be six tasks.", 6, tasks2.size());
	}

	@Test
	public void testGetById() {
		taskPriorityDao.saveOrUpdate(priority);
		taskCategoryDao.saveOrUpdate(category);
		taskStateDao.saveOrUpdate(state);
		userDao.saveOrUpdate(user);

		long idCategory = category.getIdTaskCategory();
		long idPriority = priority.getIdTaskPriority();
		long idState = state.getIdTaskState();
		long idUser = user.getIdUser();
		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);

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

		long idCategory = category.getIdTaskCategory();
		long idPriority = priority.getIdTaskPriority();
		long idState = state.getIdTaskState();
		long idUser = user.getIdUser();
		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);

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

		long idCategory = category.getIdTaskCategory();
		long idPriority = priority.getIdTaskPriority();
		long idState = state.getIdTaskState();
		long idUser = user.getIdUser();
		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);

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

		long idCategory = category.getIdTaskCategory();
		long idPriority = priority.getIdTaskPriority();
		long idState = state.getIdTaskState();
		long idUser = user.getIdUser();
		Date rightnow = Calendar.getInstance().getTime();

		Task t1 = new Task("My first task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);
		Task t2 = new Task("My second task", "This is a task", rightnow,
				rightnow, idCategory, idPriority, idState, idUser, idUser,
				"okey", 0);

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
}
