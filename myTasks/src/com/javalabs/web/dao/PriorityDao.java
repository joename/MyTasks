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
@Component("priorityDao")
public class PriorityDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(Priority priority) {
		session().saveOrUpdate(priority);
	}

	@SuppressWarnings("unchecked")
	public List<Priority> getAllPriorities() {
		return session().createQuery("from Priority").list();
	}

	public Priority get(long id) {
		Criteria crit = session().createCriteria(Priority.class);
		crit.add(Restrictions.idEq(id));
		return (Priority) crit.uniqueResult();
	}

	public Priority get(String priorityname) {
		Criteria crit = session().createCriteria(Priority.class);
		crit.add(Restrictions.ilike("priorityname", priorityname));
		return (Priority) crit.uniqueResult();
	}

	public void delete(long id) {
		Priority priorityToDel = (Priority) session().load(Priority.class, id);
		session().delete(priorityToDel);
	}
}
