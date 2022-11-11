package com.sagor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameIsActiveTrue(String username);

}
