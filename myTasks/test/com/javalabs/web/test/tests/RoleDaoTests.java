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

import com.javalabs.web.dao.Role;
import com.javalabs.web.dao.RoleDao;

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

	Role role1 = new Role("sherif");
	Role role2 = new Role("cop");
	Role role3 = new Role("farmer");
	Role role4 = new Role("citizen");
	Role role5 = new Role("neighbour");
	Role role6 = new Role("fireman");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		logger.info("Init CategoryDaoTests...");
		jdbc.execute("delete from t_role");
	}

	@Test
	public void testCreateRetrieve() {
		roleDao.saveOrUpdate(role1);
		roleDao.saveOrUpdate(role2);
		roleDao.saveOrUpdate(role3);
		roleDao.saveOrUpdate(role4);

		List<Role> roles = roleDao.getAllRoles();
		assertEquals("Should be one role.", 4, roles.size());

		Role roleR1 = roleDao.get(role1.getIdRole());

		assertEquals("Retrieved role should equal inserted role.", role1,
				roles.get(0));

		roleDao.saveOrUpdate(role5);
		roleDao.saveOrUpdate(role6);

		List<Role> roles2 = roleDao.getAllRoles();
		assertEquals("Should be six roles.", 6,roles2.size());
	}

	@Test
	public void testGetById() {
		roleDao.saveOrUpdate(role1);
		roleDao.saveOrUpdate(role2);
		roleDao.saveOrUpdate(role3);
		roleDao.saveOrUpdate(role4);

		Role roleR1 = roleDao.get(role1.getIdRole());
		assertEquals("Roles should match", role1, roleR1);
	}

	@Test
	public void testUpdate() {
		roleDao.saveOrUpdate(role1);
		roleDao.saveOrUpdate(role2);

		role2.setRolename("modifiedRole");
		roleDao.saveOrUpdate(role2);

		Role retrieved = roleDao.get(role2.getIdRole());
		assertEquals("Retrieved role should be updated.", role2, retrieved);
	}

	@Test
	public void testGetRolename() {
		roleDao.saveOrUpdate(role1);
		roleDao.saveOrUpdate(role2);

		Role roleR1 = roleDao.get(role1.getRolename());
		assertNotNull("Should be one role.",  roleR1);

		Role roleR2 = roleDao.get("abcd");
		assertNull("Should be zeri roles.", roleR2);
	}

	@Test
	public void testDelete() {
		roleDao.saveOrUpdate(role1);
		roleDao.saveOrUpdate(role2);

		Role roleR1 = roleDao.get(role1.getIdRole());
		assertNotNull("Role with ID " + role1.getIdRole()
				+ " should not be null (deleted, actual)", roleR1);

		long idRole2 = role2.getIdRole();
		
		roleDao.delete(idRole2);

		Role roleR2 = roleDao.get(idRole2);
		
		assertNull("Role with ID " + idRole2
				+ " should be null (deleted, actual)", roleR2);
	}
}
