package com.javalabs.web.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryPropertyEditor;
import com.javalabs.web.dao.Priority;
import com.javalabs.web.dao.PriorityPropertyEditor;
import com.javalabs.web.dao.State;
import com.javalabs.web.dao.StatePropertyEditor;
import com.javalabs.web.dao.Task;
import com.javalabs.web.dao.TaskAction;
import com.javalabs.web.dao.User;
import com.javalabs.web.dao.UserPropertyEditor;
import com.javalabs.web.service.CategoryService;
import com.javalabs.web.service.PriorityService;
import com.javalabs.web.service.StateService;
import com.javalabs.web.service.TaskActionService;
import com.javalabs.web.service.TaskService;
import com.javalabs.web.service.UserService;

@Controller
public class TaskController {

	private TaskService taskService;
	private TaskActionService taskActionService;
	private CategoryService categoryService;
	private UserService userService;
	private StateService stateService;
	private PriorityService priorityService;

	private static Logger logger = Logger.getLogger(TaskController.class);

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
	public void initBinder(WebDataBinder binder, HttpServletRequest req) {
		logger.info("Task controller initBinder...");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		StatePropertyEditor spe = new StatePropertyEditor();
		spe.setStateService(stateService);
		binder.registerCustomEditor(State.class, spe);
		CategoryPropertyEditor cpe = new CategoryPropertyEditor();
		cpe.setCategoryService(categoryService);
		binder.registerCustomEditor(Category.class, cpe);
		PriorityPropertyEditor ppe = new PriorityPropertyEditor();
		ppe.setPriorityService(priorityService);
		binder.registerCustomEditor(Priority.class, ppe);
		UserPropertyEditor upe = new UserPropertyEditor();
		upe.setUserService(userService);
		binder.registerCustomEditor(User.class, "userResponsible", upe);
	}

	@RequestMapping(value = "/task/lst", method = RequestMethod.GET)
	public String getTaskList(Model model) {
		logger.info("Task controller task list...");

		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);

		return "tasklst";
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.GET)
	public String createTask(Model model) {
		logger.info("Task controller task add GET...");

		List<Category> categories = categoryService.getAllCategories();
		List<Priority> priorities = priorityService.getAllPriorities();
		List<State> states = stateService.getAllStates();
		List<User> users = userService.getAllUsers();

		model.addAttribute("task", new Task());
		model.addAttribute("categories", categories);
		model.addAttribute("priorities", priorities);
		model.addAttribute("states", states);
		model.addAttribute("users", users);

		return "taskadd";
	}

	@RequestMapping(value = "/task/add", method = RequestMethod.POST)
	public String postTaskAdd(Model model, @Valid Task task,
			BindingResult result, Principal principal) {

		logger.info("Task controller task add POST...");
		String notifications = "";

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

			return "taskadd";
		}
		if (principal != null) {
			task.setDate(new Date());
			task.setUser(userService.get(principal.getName()));
			taskService.saveOrUpdate(task);
			notifications = "Task succesfully created";
		} else {
			notifications = "Task can not be created";
		}
		model.addAttribute("notifications", notifications);

		List<Task> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);

		return "tasklst";
	}

	@RequestMapping(value = "/task/upd/{id}", method = RequestMethod.GET)
	public String getTaskUpdate(@PathVariable("id") long idTask, Model model,
			Principal principal) {

		logger.info("Task controller task upd GET...");
		logger.info("-----------(" + taskService.get(idTask).getIdTask() + ") "
				+ taskService.get(idTask).getDate() + " | "
				+ taskService.get(idTask).getUser());

		List<Category> categories = categoryService.getAllCategories();
		List<Priority> priorities = priorityService.getAllPriorities();
		List<State> states = stateService.getAllStates();
		List<User> users = userService.getAllUsers();

		model.addAttribute("task", taskService.get(idTask));
		model.addAttribute("categories", categories);
		model.addAttribute("priorities", priorities);
		model.addAttribute("states", states);
		model.addAttribute("users", users);

		return "taskupd";
	}

	@RequestMapping(value = "/task/upd", method = RequestMethod.POST)
	public String postTaskUpdate(Model model, @Valid Task task,
			BindingResult result, Principal principal) {

		logger.info("Task controller task upd POST...");
		logger.info("-------------------- (" + task.getIdTask() + ") "
				+ task.getDate() + " | " + task.getUser());
		logger.info("-------" + result.getErrorCount());
		logger.info("-------" + result.getAllErrors());

		String notifications = "";

		if (result.hasErrors()) {
			notifications = "Task can not be updated, fix the errors.";
			model.addAttribute("notifications", notifications);

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

			return "taskupd";
		}
		taskService.saveOrUpdate(task);
		notifications = "Task succesfully updated";
		model.addAttribute("notifications", notifications);

		return "tasklst";
	}

	@RequestMapping(value = "/task/del/{id}", method = RequestMethod.GET)
	public String getTaskDelete(@PathVariable("id") long idTask,
			Principal principal, Model model) {
		logger.info("Task controller task del GET...");
		String notifications = "";

		if (principal == null) {
			notifications = "Any user is authenthicated.";
		} else {
			String user = principal.getName();
			if (user == taskService.get(idTask).getUser().getUsername()) {
				taskService.delete(idTask);
			}
			notifications = "Task (" + idTask + ") succesfully deleted.";
		}
		model.addAttribute("notifications", notifications);
		List<Task> tasks = taskService.getAllTasks();

		model.addAttribute("tasks", tasks);
		return "tasklst";
	}

	// value = "/getactions/{id}" AND , @PathVariable Long id
	@RequestMapping(value = "/getactions", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getActions(Principal principal) {
		logger.info("Task controller get actions...");

		List<TaskAction> actions = null;

		if (principal == null) {
			actions = new ArrayList<TaskAction>();
		} else {
			// long idTask = (id == null ? 0 : id);
			String name = principal.getName();
			actions = taskActionService.getAllTaskActions();
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("actions", actions);
		data.put("number", actions.size());

		return data;
	}
}
