package com.personal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.personal.bean.SomeBean;

@RestController
public class FilteringController {
	
	/*
	 * Dynamic filtering - takes place at the controller layer
	 * 					 - dynamically decides which fields are
	 * 						ignored based on the request
	 */
	
	// only return field1,field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		
		SomeBean someBean = new SomeBean("slyraccoon", "slyraccoon@gmail.com", "password");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(setMappingFilters("username", "email"));
		
		return mapping;
	}
	
	// only return field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		
		List<SomeBean> beanList = Arrays.asList(new SomeBean("slyraccoon", "slyraccoon@gmail.com", "password"),
				 new SomeBean("cunningfox", "fox@gmail.com", "password2"),
				 new SomeBean("eaglevantage", "vantage@gmail.com", "password3"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(beanList);
		mapping.setFilters(setMappingFilters("email", "password"));
		
		return mapping;
	}
	
	// Method to modularize filtering
	private FilterProvider setMappingFilters(String filter1, String filter2) {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
											.filterOutAllExcept(filter1, filter2);
		FilterProvider filters = new SimpleFilterProvider()
										.addFilter("SomeBeanFilter", filter);
		
		return filters;
	}	
}
