package com.javalabs.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalabs.web.dao.User;
import com.javalabs.web.service.UserService;

@Controller
public class LoginController {
    
	private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/login")
    public String showLogin(){
        return "login";
    }
    
    @RequestMapping("/createuser")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        return "createuser";
    }

    @RequestMapping(value="/docreateuser", method = RequestMethod.POST)
    public String doCreateUser(Model model, @Valid User user, BindingResult result){
        System.out.println(">LoginController doCreateUser " + user);

        if (result.hasErrors()) {
            return "createuser";
        } else {
        	userService.create(user);
            System.out.println("Form does validate");
        }
        return "usercreated";
    }
}
