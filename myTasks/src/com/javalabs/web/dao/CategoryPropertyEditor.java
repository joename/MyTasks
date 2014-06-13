package com.javalabs.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.javalabs.web.service.CategoryService;

public class CategoryPropertyEditor extends PropertyEditorSupport {
		 
	    private CategoryService categoryService;
	    
	    @Autowired
	    public void setCategoryService(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }
	 
	    @Override
	    public void setAsText(String text) throws IllegalArgumentException {
	        Long categoryId = new Long(text);
	        super.setValue(categoryService.get(categoryId));
	    }
}
