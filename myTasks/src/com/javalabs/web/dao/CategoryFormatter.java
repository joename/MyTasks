package com.javalabs.web.dao;

import java.text.ParseException;
import java.util.Locale;

import com.javalabs.web.service.CategoryService;

//@Component("CategoryFormatter")
public class CategoryFormatter {

    //@Autowired
    private CategoryService categoryService;
    //Some service class which can give the Actor after
    //fetching from Database

    public String print(Category category, Locale loc) {
          return category.getCategory().toString();
    }

    public Category parse(String idCategory, Locale loc) throws ParseException {
          return categoryService.getCategory(Long.parseLong(idCategory));
          //Else you can just return a new object by setting some values
          //which you deem fit.
     }
    
}
