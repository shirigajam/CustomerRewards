package com.rewards.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RewardsExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<RewardsErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		RewardsErrorMessage message = new RewardsErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage());

		return new ResponseEntity<RewardsErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RewardsErrorMessage> globalExceptionHandler(Exception ex, HttpServletRequest request) {
		RewardsErrorMessage message = new RewardsErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage());

		return new ResponseEntity<RewardsErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
