package com.personal.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Versioning RESTful Services
 * 
 * Basic Approach with URIs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
	
	private String firstName;
	private String lastName;
}
