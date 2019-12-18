package com.dinesh.rest.webservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.rest.webservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitServiceConfigController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveConfigvalues()
	{
		return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
	}
}
