package com.example.dinesh.filtering;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class ContentFiltering {
	
	@GetMapping("/filtering")
	public SomeBean retrieveBean() {
		return new SomeBean("value1","value2","value3");
		}
	
	@RequestMapping(method=RequestMethod.GET,path="/filtering-list")
	public List<SomeBean> retrieveAllSomeBeans()
	{
		
	return 	Arrays.asList(new SomeBean("value11","value12","value13"),
			new SomeBean("value22","value22","value23"));
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveDynamicFilterBean() {
		
		DynamicFilterBean dynamicFilterBean=new DynamicFilterBean("Dinesh","Dasaratharao",new Date(),3453);;
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(dynamicFilterBean);
		/*the below line of code will filter all the attributes except firstName and lastName*/
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("firstName","lastName");
		FilterProvider filters=new SimpleFilterProvider().addFilter("DynamicFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
		
	} 

}
