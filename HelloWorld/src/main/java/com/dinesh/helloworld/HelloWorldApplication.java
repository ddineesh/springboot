package com.dinesh.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@SpringBootApplication
public class HelloWorldApplication {

	
	@RequestMapping("/")
	@ResponseBody
	String home()
	{
		return "Hello World from Dinesh";
	}
	
	@RequestMapping("/greetings")
	@ResponseBody
	String greetingsMessage()
	{
		return "Hello Have Good one!!!!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
