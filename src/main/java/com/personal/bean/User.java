package com.personal.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  One to Many
 * 	
 * 	One user can have many Posts
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=2, message="Name should have at least 2 characters")
	private String name;
	
	@Past
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
}
