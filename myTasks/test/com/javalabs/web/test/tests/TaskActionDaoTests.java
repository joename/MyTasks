package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

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
import com.javalabs.web.dao.Role;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StateDao;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionDao;
import com.javalabs.web.dao.TaskDao;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/javalabs/web/config/dao-context.xml",
    "classpath:com/javalabs/web/config/security-context.xml",
    "classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskActionDaoTests {

  private static Logger logger = Logger.getLogger(TaskActionDaoTests.class);

  @Autowired
  private TaskDao taskDao;
  @Autowired
  private TaskActionDao taskActionDao;
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
  private User user = new User("jose", "joe", "jose", true, new Role("ROLE_USER"));

  @Before
  public void init() {
    JdbcTemplate jdbc = new JdbcTemplate(dataSource);
    logger.info("Init TestActionDaoTests...");

    jdbc.execute("delete from t_ifoctarea");
    jdbc.execute("delete from t_ifoctareaaccion");
    logger.info("---- before delete t_ifocusuario...");
    jdbc.execute("delete from t_ifocusuario");
    logger.info("---- after delete t_ifocusuario...");
    jdbc.execute("delete from a_ifoctareacategoria");
    jdbc.execute("delete from a_tareaprioridad");
    jdbc.execute("delete from a_tareaestado");
  }

  @Test
  public void testTaskActionCreate() {
    logger.info("---- Init testTaskActionCreate...");
    taskPriorityDao.saveOrUpdate(priority);
    taskCategoryDao.saveOrUpdate(category);
    taskStateDao.saveOrUpdate(state);
    userDao.saveOrUpdate(user);

    logger.info("---- new Date instance...");
    Date rightnow = Calendar.getInstance().getTime();
    logger.info(" before new task");
    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category, priority,
        state, user, user, "okey", 0);
    logger.info(" after new task");
    logger.info(" before new taskaction1");
    TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
        user, 20);
    logger.info(" before new taskaction2");
    TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
        user, 10);
    logger.info(" before new taskaction3");
    TaskAction ta3 = new TaskAction(t1, rightnow, "Task action 3", "Task action 3 description",
        user, 25);
    logger.info(" before new taskaction4");
    TaskAction ta4 = new TaskAction(t1, rightnow, "Task action 4", "Task action 4 description",
        user, 5);

    t1.addAction(ta1);
    t1.addAction(ta2);
    t1.addAction(ta3);
    t1.addAction(ta4);
    logger.info(" before task save1");
    taskDao.save(t1);
    logger.info(" before new taskaction1");
    Task t2a = new Task("My first task", "This is a task", rightnow, rightnow, category, priority,
        state, user, user, "okey", 0);
    logger.info(" before new taskaction21");
    TaskAction ta21 = new TaskAction(t2a, rightnow, "Task action 1", "Task action 21 description",
        user);
    t2a.addAction(ta21);
    logger.info(" before task save2");
    taskDao.save(t2a);

    assertTrue("Task duration should be 60 min.", t1.getDuration() == 60);
    assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, t1.getActions().size());
    assertEquals("Should be 5 taskActions with getAllTaskActions.", 5, taskActionDao
        .getAllTaskActions().size());

    Task t2 = taskDao.get(t1.getIdTask());
    assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, t2.getActions().size());
    assertEquals("Should be 4 taskActions with getAllTaskActions.", 4, taskActionDao
        .getAllTaskActions(t2.getIdTask()).size());
  }

  @Test
  public void testTaskActionUpdate() {
    taskPriorityDao.saveOrUpdate(priority);
    taskCategoryDao.saveOrUpdate(category);
    taskStateDao.saveOrUpdate(state);
    userDao.saveOrUpdate(user);

    Date rightnow = Calendar.getInstance().getTime();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category, priority,
        state, user, user, "okey", 0);

    taskDao.saveOrUpdate(t1);

    TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
        user);
    TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
        user);

    taskActionDao.saveOrUpdate(ta1);
    taskActionDao.saveOrUpdate(ta2);

    ta2.setActionname("modified update taskAction");
    ta2.setDate(Calendar.getInstance().getTime());
    ta2.setActionname("Action modified");
    ta2.setDescription("Description modified");
    ta2.setDuration(ta2.getDuration() + 20);

    taskActionDao.saveOrUpdate(ta2);

    assertTrue("TaskAction update should return true", ta2.equals(ta2));
  }

  @Test
  public void testTaskActionDelete() {
    taskPriorityDao.saveOrUpdate(priority);
    taskCategoryDao.saveOrUpdate(category);
    taskStateDao.saveOrUpdate(state);
    userDao.saveOrUpdate(user);

    Date rightnow = Calendar.getInstance().getTime();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category, priority,
        state, user, user, "okey", 0);

    taskDao.saveOrUpdate(t1);

    TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
        user);

    taskActionDao.saveOrUpdate(ta1);

    long idTaskAction = ta1.getIdTaskAction();
    taskActionDao.delete(idTaskAction);
    assertTrue("TaskAction deletion should return true", null == taskActionDao.get(idTaskAction));
  }

  @Test
  public void testTaskActionOnetoMany() {
    taskPriorityDao.saveOrUpdate(priority);
    taskCategoryDao.saveOrUpdate(category);
    taskStateDao.saveOrUpdate(state);
    userDao.saveOrUpdate(user);

    Date rightnow = Calendar.getInstance().getTime();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, category, priority,
        state, user, user, "okey", 0);

    TaskAction ta1 = new TaskAction(t1, rightnow, "Task action 1", "Task action 1 description",
        user);
    TaskAction ta2 = new TaskAction(t1, rightnow, "Task action 2", "Task action 2 description",
        user);
    TaskAction ta3 = new TaskAction(t1, rightnow, "Task action 3", "Task action 3 description",
        user);
    TaskAction ta4 = new TaskAction(t1, rightnow, "Task action 4", "Task action 4 description",
        user);

    t1.addAction(ta1);
    t1.addAction(ta2);
    t1.addAction(ta3);
    t1.addAction(ta4);

    taskDao.save(t1);

    System.out.println("id tarea" + t1.getIdTask());
    System.out.println("taskAction size " + taskActionDao.getAllTaskActions().size());

    assertEquals("Should be 4 taskActions.", 4, t1.getActions().size());
  }
}