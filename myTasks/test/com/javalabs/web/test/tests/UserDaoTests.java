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
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from t_task");
	    jdbc.execute("delete from t_taskaction");
	    jdbc.execute("delete from t_user");
	    jdbc.execute("delete from a_taskcategory");
	    jdbc.execute("delete from a_taskpriority");
	    jdbc.execute("delete from a_taskstate");
	}
	
	@Test
	public void testUserCreate(){
		User user = new User("jose","jose","jose@javalabs.com",false, "joe");
		assertTrue("User creation should return true", userDao.create(user));
	}
	
	@Test
	public void testUserBatchCreate() {
		User user1 = new User("jose","jose","jose@javalabs.com",false, "joe");
		User user2 = new User("jose1","jose1","jose1@javalabs.com",false, "joe1");
		User user3 = new User("jose2","jose2","jose2@javalabs.com",false, "joe2");
		User user4 = new User("jose3","jose3","jose3@javalabs.com",false, "joe3");

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		int rvals[]= userDao.create(users);

		int counter=0;
		for(int value:rvals){
			System.out.println("Updated " + value + "row");
			counter++;
		}
		assertTrue("State batch creation should return true",
				counter==4);
	}
	
	@Test
	public void testUserUpdate(){
		User user = new User("jose","jose","jose@javalabs.com",true, "joe");
		userDao.create(user);
		
		List<User> users = userDao.getUsers();
		User usr = users.get(0);
		
		assertTrue("User creation should return true", userDao.update(usr));
	}

	@Test
	public void testUserDelete(){
		User user = new User("jose","jose","jose@javalabs.com",false, "joe");
		userDao.create(user);
		
		List<User> users = userDao.getUsers();
		User usr = users.get(0);
		
		assertTrue("User creation should return true", userDao.delete(usr.getIdUser()));
	}
}
