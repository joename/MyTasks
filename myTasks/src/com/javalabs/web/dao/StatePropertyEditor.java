package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.StateService;

public class StatePropertyEditor extends PropertyEditorSupport {
		 
	    private StateService stateService;
	    
	    @Autowired
	    public void setStateService(StateService stateService) {
	        this.stateService = stateService;
	    }
	 
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        Long stateId = new Long(text);
	        super.setValue(stateService.get(stateId));
	    }
}
