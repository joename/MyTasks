package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

  @Before
  public void init() {
    JdbcTemplate jdbc = new JdbcTemplate(dataSource);
    logger.info("Init TestActionDaoTests...");

    jdbc.execute("delete from t_task");
    jdbc.execute("delete from t_taskaction");
    jdbc.execute("delete from t_user");
    jdbc.execute("delete from a_taskcategory");
    jdbc.execute("delete from a_taskpriority");
    jdbc.execute("delete from a_taskstate");
  }

  @Test
  public void testTaskActionCreate() {
    Category category = new Category("deutsch");
    Priority priority = new Priority("low");
    State state = new State("pending");
    User user = new User("jose", "jose", "jose@javalabs.com", true, "joe");

    taskCategoryDao.create(category);
    taskPriorityDao.create(priority);
    taskStateDao.create(state);
    userDao.create(user);

    Date rightnow = Calendar.getInstance().getTime();
    long idUser = userDao.get("jose").getIdUser();
    long idCategory = taskCategoryDao.get("deutsch").getIdTaskCategory();
    long idState = taskStateDao.get("pending").getIdTaskState();
    long idPriority = taskPriorityDao.get("low").getIdTaskPriority();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, idCategory,
                       idPriority, idState, idUser, idUser, "okey", 0, rightnow);

    taskDao.create(t1);

    t1 = taskDao.getTasks().get(0);
    rightnow = Calendar.getInstance().getTime();

    TaskAction ta = new TaskAction(t1.getIdTask(), rightnow, "Task action 1",
                                   "Task action 1 description", idUser);
    assertTrue("TaskAction creation should return true", taskActionDao.create(ta));
  }

  @Test
  public void testTaskActionBatchCreate() {
    Category category = new Category("deutsch");
    Priority priority = new Priority("low");
    State state = new State("pending");
    User user = new User("jose", "jose", "jose@javalabs.com", true, "joe");

    taskCategoryDao.create(category);
    taskPriorityDao.create(priority);
    taskStateDao.create(state);
    userDao.create(user);

    Date rightnow = Calendar.getInstance().getTime();
    long idUser = userDao.get("jose").getIdUser();
    long idCategory = taskCategoryDao.get("deutsch").getIdTaskCategory();
    long idState = taskStateDao.get("pending").getIdTaskState();
    long idPriority = taskPriorityDao.get("low").getIdTaskPriority();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, idCategory,
                       idPriority, idState, idUser, idUser, "okey", 0, rightnow);

    taskDao.create(t1);

    t1 = taskDao.getTasks().get(0);
    rightnow = Calendar.getInstance().getTime();

    TaskAction taskAction1 = new TaskAction(t1.getIdTask(), rightnow, "Task action 1",
                                            "Task action 1 description", idUser);
    TaskAction taskAction2 = new TaskAction(t1.getIdTask(), rightnow, "Task action 2",
                                            "Task action 2 description", idUser);
    TaskAction taskAction3 = new TaskAction(t1.getIdTask(), rightnow, "Task action 3",
                                            "Task action 3 description", idUser);
    TaskAction taskAction4 = new TaskAction(t1.getIdTask(), rightnow, "Task action 4",
                                            "Task action 4 description", idUser);

    List<TaskAction> taskActions = new ArrayList<TaskAction>();
    taskActions.add(taskAction1);
    taskActions.add(taskAction2);
    taskActions.add(taskAction3);
    taskActions.add(taskAction4);

    int rvals[] = taskActionDao.create(taskActions);

    int counter = 0;
    for (int value : rvals) {
      System.out.println("Updated " + value + "row");
      counter++;
    }
    assertTrue("TaskAction batch creation should return true", counter == 4);
  }

  @Test
  public void testTaskActionUpdate() {
    Category category = new Category("deutsch");
    Priority priority = new Priority("low");
    State state = new State("pending");
    User user = new User("jose", "jose", "jose@javalabs.com", true, "joe");

    taskCategoryDao.create(category);
    taskPriorityDao.create(priority);
    taskStateDao.create(state);
    userDao.create(user);

    Date rightnow = Calendar.getInstance().getTime();
    long idUser = userDao.get("jose").getIdUser();
    long idCategory = taskCategoryDao.get("deutsch").getIdTaskCategory();
    long idState = taskStateDao.get("pending").getIdTaskState();
    long idPriority = taskPriorityDao.get("low").getIdTaskPriority();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, idCategory,
                       idPriority, idState, idUser, idUser, "okey", 0, rightnow);

    taskDao.create(t1);

    t1 = taskDao.getTasks().get(0);
    rightnow = Calendar.getInstance().getTime();

    TaskAction ta = new TaskAction(t1.getIdTask(), rightnow, "Task action 1",
                                   "Task action 1 description", idUser);
    taskActionDao.create(ta);

    TaskAction ta2 = taskActionDao.getTaskActions().get(0);

    ta2.setAction("modified no update ");
    ta2.setDate(Calendar.getInstance().getTime());

    assertTrue("TaskAction update should return true", taskActionDao.update(ta2));
  }

  @Test
  public void testTaskActionDelete() {

    Category category = new Category("deutsch");
    Priority priority = new Priority("low");
    State state = new State("pending");
    User user = new User("jose", "jose", "jose@javalabs.com", true, "joe");

    taskCategoryDao.create(category);
    taskPriorityDao.create(priority);
    taskStateDao.create(state);
    userDao.create(user);

    Date rightnow = Calendar.getInstance().getTime();
    long idUser = userDao.get("jose").getIdUser();
    long idCategory = taskCategoryDao.get("deutsch").getIdTaskCategory();
    long idState = taskStateDao.get("pending").getIdTaskState();
    long idPriority = taskPriorityDao.get("low").getIdTaskPriority();

    Task t1 = new Task("My first task", "This is a task", rightnow, rightnow, idCategory,
                       idPriority, idState, idUser, idUser, "okey", 0, rightnow);

    taskDao.create(t1);

    t1 = taskDao.getTasks().get(0);
    rightnow = Calendar.getInstance().getTime();

    TaskAction ta = new TaskAction(t1.getIdTask(), rightnow, "Task action 1",
                                   "Task action 1 description", idUser);
    taskActionDao.create(ta);
    TaskAction ta1 = taskActionDao.getTaskActions().get(0);
    
    assertTrue("TaskAction deletion should return true",
               taskActionDao.delete(ta1.getIdTaskAction()));
  }
}
