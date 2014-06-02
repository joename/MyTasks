package com.javalabs.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("roleDao")
public class RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(Role role) {
		session().saveOrUpdate(role);
	}

	public void delete(long idRole) {
		Role roleToDel = (Role) session().load(Role.class, idRole);
		session().delete(roleToDel);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return session().createQuery("from Role").list();
	}

	public Role get(long idRole) {
		Criteria crit = session().createCriteria(Role.class);
		crit.add(Restrictions.idEq(idRole));
		return (Role) crit.uniqueResult();
	}

	public Role get(String rolename) {
		Criteria crit = session().createCriteria(Role.class);
		crit.add(Restrictions.ilike("rolename", rolename));
		return (Role) crit.uniqueResult();
	}

	public boolean exists(String rolename) {
		Role role = this.get(rolename);
		return role != null;
	}
}
