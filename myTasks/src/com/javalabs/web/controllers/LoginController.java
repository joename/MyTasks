package com.javalabs.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalabs.web.dao.FormValidationGroup;
import com.javalabs.web.dao.User;
import com.javalabs.web.service.UserService;

@Controller
public class LoginController {

	private UserService userService;

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping(value={"/admin"})
	public String showAdmin(Model model) {
		logger.info("Showing admin page...");
		try{
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		} catch(Exception e){
			System.out.println("Exception " + e.getClass());
		}
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/createuser")
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		return "createuser";
	}

	@RequestMapping(value="/docreateuser", method = RequestMethod.POST)
    public String doCreateUser(Model model, @Validated(FormValidationGroup.class) User user, BindingResult result){
        System.out.println(">LoginController doCreateUser " + user);

    	logger.info(">LoginController doCreateUser " + user);
		System.out.println("DUPLICATE" + userService.exists(user.getUsername()));
		
    	if(userService.exists(user.getUsername())) {
    		System.out.println("Duplicate username");
        	result.rejectValue("username", "DuplicateKey.user.username","This username already exists.");
			return "createuser";
		}
    	
    	if (result.hasErrors()) {
            return "createuser";
        }

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

    	try {
        	userService.saveOrUpdate(user);
            System.out.println("Form does validate");
        } catch (DuplicateKeyException e){
        	result.rejectValue("username", "DuplicateKey.user.username","This username already exists.");
			return "createuser";
    }
        return "usercreated";
    }
	
	
}
