package com.example.dinesh.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentFiltering {
	
	@GetMapping("/filtering")
	public SomeBean retrieveBean() {
		return new SomeBean("value1","value2","value3");
		}

}
