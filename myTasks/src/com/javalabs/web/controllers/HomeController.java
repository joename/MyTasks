package com.javalabs.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value={"/","/home"})
	public String showHome() {
		logger.info("Home controller home page...");
		return "home";
	}
	
}
