package com.sagor.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByuserIdAndIsActiveTrue(Long userId);

	User findBynameAndIsActiveTrue(String name);

	List<User> findAllByIsActiveTrue();

	// User findByuserId(Long userId);

}
