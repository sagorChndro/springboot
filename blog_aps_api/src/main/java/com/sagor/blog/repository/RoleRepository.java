package com.sagor.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
