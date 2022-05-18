package com.personal.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Static Filtering - defining what is ignored on the bean
 * use: 1. @JsonIgnoreProperties(value={"field1", "password"}) - issues with hardcoded values
 * 			on bean class
 * or   2. @JsonIgnore on field
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SomeBeanFilter") //used for Dynamic Filtering
public class SomeBean {
	
	private String username;
	
	private String email;
	
	private String password;
}
