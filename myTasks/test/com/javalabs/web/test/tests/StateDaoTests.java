package com.javalabs.web.test.tests;

import static org.junit.Assert.*;

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

import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StateDao;

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

	private State state1 = new State("pending");
	private State state2 = new State("open");
	private State state3 = new State("resolved");
	private State state4 = new State("not resolved");
	private State state5 = new State("keep it for later");
	private State state6 = new State("keep it undone");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init StateDaoTests...");

        jdbc.execute("delete from t_task");
        jdbc.execute("delete from a_taskcategory");
        jdbc.execute("delete from a_taskState");
	}

	@Test
	public void testCreateRetrieve() {
		stateDao.saveOrUpdate(state1);
		stateDao.saveOrUpdate(state2);
		stateDao.saveOrUpdate(state3);
		stateDao.saveOrUpdate(state4);

		List<State> states = stateDao.getAllStates();
		assertEquals("Should be one state.", 4, states.size());

		State stateR1 = stateDao.get(state1.getIdTaskState());

		assertEquals("Retrieved state should equal inserted state.", stateR1,
				states.get(0));

		stateDao.saveOrUpdate(state5);
		stateDao.saveOrUpdate(state6);

		List<State> states2 = stateDao.getAllStates();
		assertEquals("Should be six states.", 6,states2.size());
	}

	@Test
	public void testGetById() {
		stateDao.saveOrUpdate(state1);
		stateDao.saveOrUpdate(state2);
		stateDao.saveOrUpdate(state3);
		stateDao.saveOrUpdate(state4);
		
		State stateR1 = stateDao.get(state1.getIdTaskState());
		assertEquals("states should match", state1, stateR1);
	}

	@Test
	public void testUpdate() {
		stateDao.saveOrUpdate(state1);
		stateDao.saveOrUpdate(state2);
		
		state2.setStatename("modifiedstate");
		stateDao.saveOrUpdate(state2);

		State retrieved = stateDao.get(state2.getIdTaskState());
		assertEquals("Retrieved state should be updated.", state2, retrieved);
	}

	@Test
	public void testGetStatename() {
		stateDao.saveOrUpdate(state1);
		stateDao.saveOrUpdate(state2);

		State stateR1 = stateDao.get(state2.getStatename());
		assertNotNull("Should be one state.",  stateR1);

		State stateR2 = stateDao.get("abcd");
		assertNull("Should be zeri states.", stateR2);
	}
	
	@Test
	public void testDelete() {
		stateDao.saveOrUpdate(state1);
		stateDao.saveOrUpdate(state2);

		State stateR1 = stateDao.get(state1.getIdTaskState());
		assertNotNull("State with ID " + state1.getIdTaskState()
				+ " should not be null (deleted, actual)", stateR1);

		stateDao.delete(state1.getIdTaskState());

		State stateR2 = stateDao.get(stateR1.getIdTaskState());
		assertNull("State with ID " + stateR1.getIdTaskState()
				+ " should be null (deleted, actual)", stateR2);
	}
}
