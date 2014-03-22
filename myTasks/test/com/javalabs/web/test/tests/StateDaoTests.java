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

import com.javalabs.web.controllers.HomeController;
import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryDao;
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
public class StateDaoTests {

	private static Logger logger = Logger.getLogger(StateDaoTests.class);

	@Autowired
	private StateDao stateDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init StateDaoTests...");

        jdbc.execute("delete from t_task");
        jdbc.execute("delete from a_taskcategory");
        jdbc.execute("delete from a_taskState");
	}

	@Test
	public void testCategoryCreate() {
		State state = new State("pending");
		assertTrue("Category creation should return true",
				stateDao.create(state));
	}

	@Test
	public void testStateBatchCreate() {
		State state1 = new State("pending");
		State state2 = new State("open");
		State state3 = new State("resolved");
		State state4 = new State("not resolved");

		List<State> states = new ArrayList<State>();
		states.add(state1);
		states.add(state2);
		states.add(state3);
		states.add(state4);
		
		int rvals[]= stateDao.create(states);

		int counter=0;
		for(int value:rvals){
			System.out.println("Updated " + value + "row");
			counter++;
		}
		assertTrue("State batch creation should return true",
				counter==4);
	}

	@Test
	public void testCategoryUpdate() {
		State state1 = new State("hello");
		
		stateDao.create(state1);
		
		State state = stateDao.get("hello");
		
		state.setState("bye");
		state.setSortOrder(21);
		
		assertTrue("State update should return true",
				stateDao.update(state));
	}

	@Test
	public void testCategoryDelete() {
		State state1 = new State("pending");
		State state2 = new State("open");
		State state3 = new State("resolved");
		State state4 = new State("not resolved");

		stateDao.create(state1);
		stateDao.create(state2);
		stateDao.create(state3);
		stateDao.create(state4);
		
		List<State> states = stateDao.getStates();
		State state = states.get(0);
		
System.out.println(state);

		assertTrue("Category deletion should return true",
				stateDao.delete(state.getIdTaskState()));
	}
}
