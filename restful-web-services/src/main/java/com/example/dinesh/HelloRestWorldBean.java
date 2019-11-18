package com.example.dinesh;

public class HelloRestWorldBean {

	String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloRestWorldBean(String message) {
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Hello World Bean [message=%s]", message);
	}
}
