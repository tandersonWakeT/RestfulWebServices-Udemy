package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Consider Simple Social Media Application
 * 
 * 	User -> Posts to website in a one to many relationship,
 * 			one user has many posts
 * 		 - Retrieve all posts for a User - GET  /users/{id}/posts
 * 		 - Create a posts for a User     - POST /users/{id}/posts
 *       - Retrieve details of a post    - GET  /users/{id}/posts/{post_id}
 */
@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
