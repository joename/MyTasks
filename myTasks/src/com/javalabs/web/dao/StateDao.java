package com.javalabs.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("stateDao")
public class StateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void saveOrUpdate(State state) {
		session().saveOrUpdate(state);
	}
	
	public State get(long id) {
		Criteria crit = session().createCriteria(State.class);
		crit.add(Restrictions.idEq(id));
		return (State) crit.uniqueResult();
	}

	public State get(String statename) {
		Criteria crit = session().createCriteria(State.class);
		crit.add(Restrictions.ilike("statename", statename));
		return (State) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<State> getAllStates() {
		return session().createQuery("from State").list();
	}

	public void delete(long id) {
		State stateToDel = (State) session().load(State.class, id);
		session().delete(stateToDel);
	}
}
