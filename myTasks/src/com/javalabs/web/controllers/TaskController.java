package com.javalabs.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    
    @RequestMapping("/tasks")
    public String showTasks(Model model) {
    	taskService.throwTestException();
        List<Task> tasks = taskService.getCurrent();
        
        //taskService.throwTestException();
        
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @RequestMapping("/createtask")
    public String createTask(Model model) {
        List<Category> categories = categoryService.getCategories();
        List<Priority> priorities = priorityService.getPriorities();
        List<State> states = stateService.getStates();
        List<User> users = userService.getUsers();

        model.addAttribute("task", new Task());
        model.addAttribute("categories", categories);
        model.addAttribute("priorities", priorities);
        model.addAttribute("states", states);
        model.addAttribute("users", users);

        return "createtask";
    }

    @RequestMapping(value = "/docreatetask", method = RequestMethod.POST)
    public String doCreateTask(Model model, @Valid Task task, BindingResult result) {
        System.out.println(">TaskController doCreateTask " + task);

        if (result.hasErrors()) {
            return "createtask";
        } else {
        	taskService.create(task);
            System.out.println(">TaskController: Form does validate");
        }
        return "taskcreated";
    }
    
    /*
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException ex){
        return "error";
    }*/
    
}
