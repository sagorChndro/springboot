package com.sagor.userservice.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.userservice.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUserIdAndIsActiveTrue(String userId);

	Set<User> findAllByIsActiveTrue();
}
