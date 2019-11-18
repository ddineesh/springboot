package com.example.dinesh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RestfulServieController {


	@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	public String helloRestWorld()
	{
		return "Hello form Restful service";
	}
	
	@GetMapping(path="/hello-world1")
	public String helloRestWorld1()
	{
		return "Hello form Restful service1";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloRestWorldBean helloRestWorldBean()
	{
		return new HelloRestWorldBean("Hello World from Bean");
	}
}
