package com.personal.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.personal.bean.User;
import com.personal.exception.UserNotFoundException;
import com.personal.service.UserDaoService;

/*
 * Also referred to as a UserResource or UserController
 * Controller naming convention fits better with MVC pattern
 */
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	//retrieve all users - GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	//retrieve user by id - GET /users/{id}
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		
		// if user is null throw exception
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		
		// return data and actions possible to perform on data
		// provide links using hateoas
		EntityModel<User> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		model.add(linkToUsers.withRel("all-users"));
		
		return model;
	}
	
	/* 
	* input - details of user
	* output - created & return the created URI
	*/
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);
		
		// Return status CREATED 201
		// /user/{id}	savedUser.getId() 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
								   				  .path("/{id}")
								   				  .buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//delete user by id - DELETE /users/{id}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		
		// if user is null throw exception
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
	}
	
}
