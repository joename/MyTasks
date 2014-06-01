package com.javalabs.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.User;
import com.javalabs.web.service.CategoryService;
import com.javalabs.web.service.PriorityService;
import com.javalabs.web.service.StateService;
import com.javalabs.web.service.TaskService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskController {

	private TaskService taskService;
	private CategoryService categoryService;
	private UserService userService;
	private StateService stateService;
	private PriorityService priorityService;

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	@Autowired
	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/tasks")
	public String showTasks(Model model) {
		List<Task> tasks = taskService.getAllTasks();

		model.addAttribute("tasks", tasks);
		return "tasks";
	}

	@RequestMapping("/createtask")
	public String createTask(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		List<Priority> priorities = priorityService.getAllPriorities();
		List<State> states = stateService.getAllStates();
		List<User> users = userService.getAllUsers();

		model.addAttribute("task", new Task());
		model.addAttribute("categories", categories);
		model.addAttribute("priorities", priorities);
		model.addAttribute("states", states);
		model.addAttribute("users", users);
		System.out.println("/createtask in here");

		return "createtask";
	}

	@RequestMapping(value = "/docreatetask", method = RequestMethod.POST)
	public String doCreateTask(Model model, @Valid Task task,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		System.out.println("Task /docreatetask: " + task);
		System.out.println("delete= " + delete);
		System.out.println("hasErrors= " + result.hasErrors());

		if (result.hasErrors()) {
			List<Category> categories = categoryService.getAllCategories();
			List<Priority> priorities = priorityService.getAllPriorities();
			List<State> states = stateService.getAllStates();
			List<User> users = userService.getAllUsers();

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);

			model.addAttribute("categories", categories);
			model.addAttribute("priorities", priorities);
			model.addAttribute("states", states);
			model.addAttribute("users", users);
			model.addAttribute("task", task);

			return "createtask";
		}

		if (delete == null) {
			if (task.getUserResponsible() == null) {
				task.setUserResponsible(null);
			}
			String username = principal.getName();
			task.setUser(userService.get(username));
			task.setDate(new Date());
			taskService.create(task);
			return "taskcreated";
		} else {
			return "taskdeleted";
		}
	}

	/*
	 * @ExceptionHandler(DataAccessException.class) public String
	 * handleDatabaseException(DataAccessException ex){ return "error"; }
	 */
}
