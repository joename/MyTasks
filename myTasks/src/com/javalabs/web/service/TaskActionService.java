package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.TaskActionDao;

@Service("taskActionService")
public class TaskActionService {

    private TaskActionDao taskActionDao;

    @Autowired
    public void setTaskDao(TaskActionDao taskActionDao) {
        this.taskActionDao = taskActionDao;
    }

    public List<TaskAction> getAllTaskActions() {
        return taskActionDao.getAllTaskActions();
    }
    
    public List<TaskAction> getAllTaskActions(long idTask) {
        return taskActionDao.getAllTaskActions(idTask);
    }

    public void saveOrUpdate(TaskAction taskAction) {
        taskActionDao.saveOrUpdate(taskAction);
    }
}