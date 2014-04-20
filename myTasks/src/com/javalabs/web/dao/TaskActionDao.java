package com.javalabs.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("taskActionDao")
public class TaskActionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(TaskAction ta) {
		session().saveOrUpdate(ta);
	}

	public void delete(long id) {
		TaskAction taToDel = (TaskAction) session().load(TaskAction.class, id);
		session().delete(taToDel);
	}

	public TaskAction get(long id) {
		Criteria crit = session().createCriteria(TaskAction.class);
		crit.add(Restrictions.idEq(id));
		return (TaskAction) crit.uniqueResult();
	}

	public TaskAction get(String taskActionName) {
		Criteria crit = session().createCriteria(TaskAction.class);
		crit.add(Restrictions.ilike("taskActionname", taskActionName));
		return (TaskAction)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskAction> getAllTaskActions() {
		return session().createQuery("from TaskAction").list();
	}
}