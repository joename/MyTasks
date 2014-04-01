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
@Component("userDao")
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {
		session().save(user);
	}

	public User get(long id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		return (User)crit.uniqueResult();
	}

	public User get(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.ilike("username", username));
		return (User)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
		// return jdbc.query("select * from t_user", new UserRowMapper());
	}

	public boolean exists(String username) {
		User user = this.get(username);
		return user != null;
	}
}