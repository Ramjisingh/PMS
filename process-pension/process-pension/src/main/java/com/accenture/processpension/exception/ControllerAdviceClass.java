package com.accenture.processpension.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ControllerAdviceClass {

	

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdviceClass.class);
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> controllerAdviceResponse(TokenException e)
	{
		LOGGER.info("STARTED - controllerAdviceResponse");
		ErrorResponse response=new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		response.setExceptionTime(System.currentTimeMillis());
		LOGGER.info("END - controllerAdviceResponse");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleWrongUserException(WrongUserException e)
	{
		LOGGER.info("STARTED - controllerAdviceResponse");
		ErrorResponse response=new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setExceptionTime(System.currentTimeMillis());
		LOGGER.info("END - controllerAdviceResponse");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse> controllerAdviceResponse(UserNotFound e)
//	{
//		LOGGER.info("STARTED - controllerAdviceResponse");
//		ErrorResponse response=new ErrorResponse();
//		response.setMessage(e.getMessage());
//		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
//		response.setExceptionTime(System.currentTimeMillis());
//		LOGGER.info("END - controllerAdviceResponse");
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	}
}

	
	
	