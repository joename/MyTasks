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

import com.javalabs.web.controllers.HomeController;
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
public class TaskDaoTests {

  private static Logger logger = Logger.getLogger(TaskDaoTests.class);

  @Autowired
  private TaskDao taskDao;
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
  private User user = new User("jose", "jose", "jose@javalabs.com", true, "joe");

  
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
  public void testTaskCreate() {
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

    assertTrue("Task creation should return true", taskDao.create(t1));
  }

  @Test
  public void testCategoryBatchCreate() {

    taskCategoryDao.create(category);
    taskPriorityDao.create(priority);
    taskStateDao.create(state);
    userDao.create(user);

    Date rightnow = Calendar.getInstance().getTime();
    long idUser = userDao.get("jose").getIdUser();
    long idCategory = taskCategoryDao.get("deutsch").getIdTaskCategory();
    long idState = taskStateDao.get("pending").getIdTaskState();
    long idPriority = taskPriorityDao.get("low").getIdTaskPriority();

    Task task1 = new Task("My first task", "This is a task", rightnow, rightnow, idCategory,
                       idPriority, idState, idUser, idUser, "okey", 0, rightnow);
    Task task2 = new Task("My second task", "This is a task", rightnow, rightnow, idCategory,
                          idPriority, idState, idUser, idUser, "okey", 0, rightnow);
    Task task3 = new Task("My third task", "This is a task", rightnow, rightnow, idCategory,
                          idPriority, idState, idUser, idUser, "okey", 0, rightnow);
    Task task4 = new Task("My forth task", "This is a task", rightnow, rightnow, idCategory,
                          idPriority, idState, idUser, idUser, "okey", 0, rightnow);
    
    List<Task> tasks = new ArrayList<Task>();
    tasks.add(task1);
    tasks.add(task2);
    tasks.add(task3);
    tasks.add(task4);

    int rvals[] = taskDao.create(tasks);

    int counter = 0;
    for (int value : rvals) {
      System.out.println("Updated " + value + "row");
      counter++;
    }
    assertTrue("Task batch creation should return true", counter == 4);
  }

  @Test
  public void testTaskUpdate() {
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
    Task t2 = taskDao.getAllTasks().get(0);
    t2.setDate(Calendar.getInstance().getTime());
    t2.setDescription("Modified task.........");

    assertTrue("Task update should return true", taskDao.update(t2));
  }

  @Test
  public void testTaskDelete() {
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
    Task t2 = taskDao.getAllTasks().get(0);
    assertTrue("Task deletion should return true", taskDao.delete(t2.getIdTask()));
  }
}
