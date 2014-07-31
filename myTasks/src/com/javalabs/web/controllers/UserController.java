package com.javalabs.web.controllers;

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
public class UserController {

	private UserService userService;

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/user/add")
	public String getUser(Model model) {
		logger.info("User add GET...");
		model.addAttribute("user", new User());
		return "useradd";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String postUserAdd(Model model,
			@Validated(FormValidationGroup.class) User user,
			BindingResult result) {
		logger.info("User add POST...");

		logger.info(">LoginController doCreateUser " + user);
		System.out
				.println("DUPLICATE" + userService.exists(user.getUsername()));

		if (userService.exists(user.getUsername())) {
			System.out.println("Duplicate username");
			result.rejectValue("username", "DuplicateKey.user.username",
					"This username already exists.");
			return "useradd";
		}

		if (result.hasErrors()) {
			return "useradd";
		}

		user.setEnabled(true);

		try {
			userService.saveOrUpdate(user);
			System.out.println("Form does validate");
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username",
					"This username already exists.");
			return "useradd";
		}
		return "usercreated";
	}
}