package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityDao;

@Service("priorityService")
public class PriorityService {

    private PriorityDao priorityDao;

    @Autowired
    public void setPriorityDao(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }

    public List<Priority> getAllPriorities() {
        return priorityDao.getAllPriorities();
    }
}
