package com.sagor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sagor.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

	@Query("select u from user u where u.fullName Like %:query% or u.email Like %:query%")
	public List<User> serachUser(@Param("query") String query);

}
