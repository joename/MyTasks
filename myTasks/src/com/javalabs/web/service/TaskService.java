package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskDao;

@Service("taskService")
public class TaskService {

    private TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getCurrent() {
        return taskDao.getAllTasks();
    }

    public void create(Task task) {
        taskDao.create(task);
    }

    public void throwTestException() {
        taskDao.getTask(12345);
    }
}
