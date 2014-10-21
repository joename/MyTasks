package com.javalabs.web.dao;

import java.util.List;

import org.hibernate.Query;
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
@Component("taskActionDao")
public class TaskActionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void save(TaskAction ta) {
        session().save(ta);
    }

    public void update(TaskAction ta) {
        session().update(ta);
    }

    public void saveOrUpdate(TaskAction ta) {
        session().saveOrUpdate(ta);
    }

    public void merge(TaskAction ta) {
        session().merge(ta);
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
        return (TaskAction) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<TaskAction> getAllTaskActions() {
        Query q = session().createQuery("from TaskAction");
        List<TaskAction> la = q.list();
        return la;
    }

    @SuppressWarnings("unchecked")
    public List<TaskAction> getAllTaskActions(long idTask) {
        Criteria crit = session().createCriteria(TaskAction.class);
        crit.add(Restrictions.eq("task.idTask", idTask));
        return (List<TaskAction>)crit.list();
    }
}