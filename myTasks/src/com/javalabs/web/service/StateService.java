package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StateDao;

@Service("stateService")
public class StateService {

    private StateDao stateDao;

    @Autowired
    private void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    public List<State> getStates() {
        return stateDao.getStates();
    }
}
