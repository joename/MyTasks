package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.CategoryService;
import com.javalabs.web.service.PriorityService;

public class PriorityPropertyEditor extends PropertyEditorSupport {
		 
	    private PriorityService priorityService;
	    
	    @Autowired
	    public void setPriorityService(PriorityService priorityService) {
	        this.priorityService = priorityService;
	    }
	 
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        Long priorityId = new Long(text);
	        super.setValue(priorityService.get(priorityId));
	    }
}
