package com.example.dinesh.user.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllUserServiceException(Exception ex, WebRequest request) throws Exception {
	
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
	
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotSavedException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotSavedException ex, WebRequest request) throws Exception {
	
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PostsNotFoundException.class)
	public final ResponseEntity<Object> handlePostsFoundException(PostsNotFoundException ex, WebRequest request) throws Exception {
	
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NO_CONTENT);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
