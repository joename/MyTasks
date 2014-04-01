package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/javalabs/web/config/dao-context.xml",
    "classpath:com/javalabs/web/config/security-context.xml",
    "classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

  @Autowired
  private UserDao userDao;

  @Autowired
  private DataSource dataSource;

  private User user1 = new User("josema", "josema", "jose@javalabs.com", false, "joe");
  private User user2 = new User("josema1", "josema1", "jose1@javalabs.com", false, "joe1");
  private User user3 = new User("josema2", "josema2", "jose2@javalabs.com", false, "joe2");
  private User user4 = new User("josema3", "josema3", "jose3@javalabs.com", false, "joe3");

  @Before
  public void init() {
    JdbcTemplate jdbc = new JdbcTemplate(dataSource);

    jdbc.execute("delete from t_task");
    jdbc.execute("delete from t_taskaction");
    jdbc.execute("delete from t_user");
    jdbc.execute("delete from a_taskcategory");
    jdbc.execute("delete from a_taskpriority");
    jdbc.execute("delete from a_taskstate");
  }

  @Test
  public void testCreateRetrieve() {
    userDao.create(user1);

    List<User> users = userDao.getAllUsers();

    assertEquals("One user should have been created and retrieved", 1, users.size());
    assertEquals("Inserted user should match user retreived", user1, users.get(0));
    
    userDao.create(user2);
    userDao.create(user3);
    userDao.create(user4);
    
    assertEquals("Should be 4 inserted users", 4, users.size());
  }

  @Test
  public void testUserBatchCreate() {

    List<User> users = new ArrayList<User>();
    users.add(user1);
    users.add(user2);
    users.add(user3);
    users.add(user4);

    int rvals[] = userDao.create(users);

    int counter = 0;
    for (int value : rvals) {
      System.out.println("Updated " + value + "row");
      counter++;
    }
    assertTrue("State batch creation should return true", counter == 4);
  }

  @Test
  public void testUserUpdate() {
    userDao.create(user1);

    List<User> users = userDao.getAllUsers();
    User usr = users.get(0);

    assertTrue("User creation should return true", userDao.update(usr));
  }

  @Test
  public void testUserDelete() {
    userDao.create(user1);

    List<User> users = userDao.getAllUsers();
    User usr = users.get(0);

    assertTrue("User creation should return true", userDao.delete(usr.getIdUser()));
  }
}
