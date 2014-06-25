package com.javalabs.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		logger.info("Logged out.");
		return "loggedout";
	}
}
