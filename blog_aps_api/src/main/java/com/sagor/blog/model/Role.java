package com.sagor.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Role {
	@Id
	private Long roleId;
	private String roleName;
	@ManyToMany
	private Set<User> users = new HashSet<>();

}
