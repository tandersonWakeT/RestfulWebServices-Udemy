package com.personal.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * Rest Controller because it provides a response back
 * in case of exceptions
 * Controller Advice allows this resource to be shared
 * across multiple controller classes
 */
@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		ExceptionDetails exceptionDetails = 
				new ExceptionDetails(new Date(), ex.getMessage(), 
									  request.getDescription(false));
		
		return new ResponseEntity(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) {
		
		ExceptionDetails exceptionDetails = 
				new ExceptionDetails(new Date(), ex.getMessage(), 
									  request.getDescription(false));
		
		return new ResponseEntity(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionDetails exceptionDetails = 
				new ExceptionDetails(new Date(), "Validation Failed", 
									  ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);	
	}
}
