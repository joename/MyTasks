package com.javalabs.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserPropertyEditor;
import com.javalabs.web.service.TaskActionService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskActionController {

	private TaskActionService taskActionService;
	private UserService userService;

	private static Logger logger = Logger.getLogger(TaskController.class);

	@Autowired
	public void setTaskActionService(TaskActionService taskActionService) {
		this.taskActionService = taskActionService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void initBinder(WebDataBinder binder, HttpServletRequest req) {
		logger.info("Task controller initBinder...");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		UserPropertyEditor upe = new UserPropertyEditor();
		upe.setUserService(userService);
		binder.registerCustomEditor(User.class, "user", upe);
	}

	@RequestMapping(value = "/taskaction/upd", method = RequestMethod.POST)
	public String postTaskUpdate(Model model,
			@ModelAttribute("action") @Valid TaskAction taskaction,
			BindingResult result, Principal principal) {

		logger.info("Task controller taskaction upd POST...");

		String notifications = "";

		if (result.hasErrors()) {
			logger.info("-------" + result.getAllErrors());
			notifications = "-Task can not be updated, fix the errors."
					+ result.getAllErrors();
			model.addAttribute("notifications", notifications);

			return "taskupd";
		} else {
			taskActionService.saveOrUpdate(taskaction);
			notifications = "+Task succesfully updated";
			model.addAttribute("notifications", notifications);

			return "tasklst";
		}
	}
}
