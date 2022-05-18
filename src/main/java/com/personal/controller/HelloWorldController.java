package com.personal.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.personal.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//method - returns "Hello World"
	// GET
	// URI - /hello-world
	@GetMapping("/hello-world")
	public String helloWorld() {
		
		return "Hello World";
	}
	
	// auto converts bean to json when accessed
	// via URI
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	// ====== Internationalization - i18n ======
	@GetMapping("/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name="Accept-Language", required=false)
			Locale locale) {
		
		//english en = Hello World
		//nl = Goede Morgen
		//french fr = Bonjour
		//spanish sp = Hola
		
		return messageSource.getMessage("greeting.message", null, "Default Greeting", locale);
		//return "Hello World International";	
	}
	
	// using LocaleContextHolder instead of passing locale as param
	@GetMapping("/hello-world-internationalizedV2")
	public String helloWorldInternationalizedV2() {
		
		//english en = Hello World
		//nl = Goede Morgen
		//french fr = Bonjour
		//spanish sp = Hola
		
		return messageSource.getMessage("greeting.message", null,
				"Default Greeting", LocaleContextHolder.getLocale());
		//return "Hello World International";	
	}
}
