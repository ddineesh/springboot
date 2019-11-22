package com.example.dinesh.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PostsNotFoundException extends RuntimeException {
	
	
	public PostsNotFoundException(String message) {
		super(message);
		
	}
	

}
