package com.zeke.crmrestapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zeke.crmrestapi.entity.Customer;
import com.zeke.crmrestapi.exception.CustomerErrorResponse;
import com.zeke.crmrestapi.exception.CustomerNotFoundException;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	
	//Add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		
		//create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		//return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//add an exception handler for generic exception
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc){
		
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(),
																exc.getMessage(),
																System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
