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
import com.javalabs.web.dao.Role;
import com.javalabs.web.dao.RoleDao;
import com.javalabs.web.dao.State;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/javalabs/web/config/dao-context.xml",
		"classpath:com/javalabs/web/config/security-context.xml",
		"classpath:com/javalabs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleDaoTests {

	private static Logger logger = Logger.getLogger(RoleDaoTests.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init CategoryDaoTests...");
		jdbc.execute("delete from t_role");
	}

	@Test
	public void testCategoryCreate() {
		Role role = new Role("sherif");
		assertTrue("Role creation should return true",
				roleDao.create(role));
	}
	
	@Test
	public void testCategoryBatchCreate() {
		Role role1 = new Role("sherif");
		Role role2 = new Role("cop");
		Role role3 = new Role("farmer");
		Role role4 = new Role("citizen");

		List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		roles.add(role4);
		
		int rvals[]= roleDao.create(roles);

		int counter=0;
		for(int value:rvals){
			System.out.println("Updated " + value + "row");
			counter++;
		}
		assertTrue("Role batch creation should return true",
				counter==4);
	}
	
	@Test
	public void testCategoryUpdate() {
		Role role1 = new Role("sherif");
		
		roleDao.create(role1);
		
		List<Role> roles = roleDao.getRoles();
		Role role = roles.get(0);
		
		role.setRolename("cop");
		
		assertTrue("Role update should return true",
				roleDao.update(role));
	}

	@Test
	public void testCategoryDelete() {
		Role role1 = new Role("cop");
		
		roleDao.create(role1);
		
		List<Role> roles = roleDao.getRoles();
		Role role = roles.get(0);
		
		assertTrue("Role deletion should return true",
				roleDao.delete(role.getIdRole()));
	}
}
