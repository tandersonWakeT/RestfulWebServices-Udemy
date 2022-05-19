package com.personal.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.personal.bean.Post;
import com.personal.bean.User;
import com.personal.exception.UserNotFoundException;
import com.personal.repository.PostRepository;
import com.personal.repository.UserRepository;
import com.personal.service.UserDaoService;

/*
 * Also referred to as a UserResource or UserController
 * Controller naming convention fits better with MVC pattern
 */
@RestController
@RequestMapping("/jpa")
public class UserJPAController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//retrieve all users - GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	//retrieve user by id - GET /users/{id}
	@GetMapping("/users/{id}")
	public EntityModel<Optional<User>> retrieveUser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		
		// if user is null throw exception
		if (!user.isPresent()) {
			throw new UserNotFoundException("id: " + id + " not found");
		}
		
		// return data and actions possible to perform on data
		// provide links using hateoas
		EntityModel<Optional<User>> model = EntityModel.of(user);
		
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
		
		User savedUser = userRepository.save(user);
		
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
		userRepository.deleteById(id);	
	}
	
	// ===== Retrieve Posts for Specific User =====
	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostsByUser(@PathVariable int id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id: " + id + " not found");
		}
		
		return userOptional.get().getPosts();
	}
	
	// ===== Create Posts for Specific User =====
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id: " + id + " not found");
		}
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		// Return status CREATED 201
		// /jpa/user/{id}/posts	post.getId() 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
										   		  .path("/{id}")
										   		  .buildAndExpand(post.getId()).toUri();
				
		return ResponseEntity.created(location).build();
	}
	
}
