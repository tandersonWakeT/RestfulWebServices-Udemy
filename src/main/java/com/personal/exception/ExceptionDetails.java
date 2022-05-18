package com.personal.exception;

import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Custom structure for exception responses
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {
	
	private Date timestamp;
	private String message;
	private String details;
}
