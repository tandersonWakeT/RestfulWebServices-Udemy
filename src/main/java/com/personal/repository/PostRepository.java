package com.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.bean.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
}
