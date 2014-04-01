package com.javalabs.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("categoryDao")
public class CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(Category category) {
		session().save(category);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		return session().createQuery("from Category").list();
	}

	public Category get(long idTaskCategory) {
		Criteria crit = session().createCriteria(Category.class);
		crit.add(Restrictions.idEq(idTaskCategory));
		return (Category)crit.uniqueResult();
	}

	public Category get(String category) {
		Criteria crit = session().createCriteria(Category.class);
		crit.add(Restrictions.ilike("category", category));
		return (Category)crit.uniqueResult();
	}
	
	public boolean exists(String categoryname) {
		Category category = this.get(categoryname);
		return category != null;
	}
}
