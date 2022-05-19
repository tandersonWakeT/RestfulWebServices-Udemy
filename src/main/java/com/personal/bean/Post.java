package com.personal.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  Many to One
 *  
 *  Many posts belong to one user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	// Details of user will not be called unless post.getUser method is called
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
}
