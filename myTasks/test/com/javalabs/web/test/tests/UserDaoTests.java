package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

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

import com.javalabs.web.dao.Role;
import com.javalabs.web.dao.RoleDao;
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
  private RoleDao roleDao;

  @Autowired
  private DataSource dataSource;

  private User user1 = new User("jose", "joe", "jose", true, new Role("ROLE_USER"));
  private User user2 = new User("josema1", "josema1", "josema1", false, new Role("ROLE_USER"));
  private User user3 = new User("josema2", "josema2", "josema2", false, new Role("ROLE_USER"));
  private User user4 = new User("josema3", "josema3", "josema3", false, new Role("ROLE_USER"));

  Role role1 = new Role("sherif");
  Role role2 = new Role("cop");

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
    userDao.saveOrUpdate(user1);

    List<User> users = userDao.getAllUsers();

    assertEquals("One user should have been created and retrieved", 1, users.size());
    assertEquals("Inserted user should match user retreived", user1, users.get(0));

    userDao.saveOrUpdate(user2);
    userDao.saveOrUpdate(user3);
    userDao.saveOrUpdate(user4);

    users = userDao.getAllUsers();

    assertEquals("Should be 4 inserted users", 4, users.size());
  }

  @Test
  public void testExists() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);
    userDao.saveOrUpdate(user3);

    assertTrue("User should exist.", userDao.exists(user2.getUsername()));
    assertFalse("User should not exist.", userDao.exists("xkjhsfjlsjf"));
  }

  @Test
  public void testGetById() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);
    userDao.saveOrUpdate(user3);
    userDao.saveOrUpdate(user4);

    User userR1 = userDao.get(user1.getIdPerson());
    assertEquals("Users should match", user1, userR1);
  }

  @Test
  public void testUpdate() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);

    user2.setUsername("modifiedUser");
    userDao.saveOrUpdate(user2);

    User retrieved = userDao.get(user2.getIdPerson());
    assertEquals("Retrieved user should be updated.", user2, retrieved);
  }

  @Test
  public void testGetUsername() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);

    User userR1 = userDao.get(user1.getUsername());
    assertEquals("Should be the same username.", userR1.getUsername(), user1.getUsername());

    User userR2 = userDao.get("abcd");
    assertNull("Should be zeri users.", userR2);
  }

  @Test
  public void testDelete() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);

    User userR1 = userDao.get(user1.getIdPerson());
    assertNotNull("User with ID " + user1.getIdPerson() + " should not be null (deleted, actual)",
        userR1);

    long idUser2 = user2.getIdPerson();

    userDao.delete(idUser2);
    User userR2 = userDao.get(idUser2);

    assertNull("User with ID " + idUser2 + " should be null (deleted, actual)", userR2);
  }

  @Test
  public void testAddUserRole() {
    userDao.saveOrUpdate(user1);
    roleDao.saveOrUpdate(role1);
    roleDao.saveOrUpdate(role2);

    user1.getRoles().add(role1);
    user1.getRoles().add(role2);

    userDao.saveOrUpdate(user1);

    assertEquals("User roles must be 2.", 2, user1.getRoles().size());
    if (role1.getUsers().size() > 0) {
      System.out.println(role1.getUsers().iterator().next().getAka());
    } else {
      System.out.println("Role1 has not users;");
    }
  }

  @Test
  public void testRetrieving() {
    userDao.saveOrUpdate(user1);
    userDao.saveOrUpdate(user2);
    userDao.saveOrUpdate(user3);

    List<User> users = userDao.getAllUsers();
    for (User u : users) {
      System.out.println(u);
    }
  }
}
