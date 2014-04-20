package com.javalabs.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	private static Logger logger = Logger.getLogger(ErrorHandler.class);
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		System.out.println(">ExceptionHandler: " + ex.getMessage());

		return "error";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		return "denied";
	}

	@ExceptionHandler(TransientDataAccessResourceException.class)
	public String handleTransientDataAccessResourceException(
			TransientDataAccessResourceException ex) {
		logger.info("Error handler: " + ex.getMessage());
		return "error";
	}
}
