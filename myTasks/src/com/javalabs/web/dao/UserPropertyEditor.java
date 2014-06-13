package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.UserService;

public class UserPropertyEditor extends PropertyEditorSupport {
		 
	    private UserService userService;
	    
	    @Autowired
	    public void setUserService(UserService userService) {
	        this.userService = userService;
	    }
	    
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        super.setValue(userService.get(Long.parseLong(text)));
	    }
}
