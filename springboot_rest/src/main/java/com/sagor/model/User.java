package com.sagor.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@RequiredArgsConstructor
public class User extends BaseModel {
	// @NotBlank(message = "Name is mandatory")
	private String name;
	// @Email(message = "Email must be valid")
	private String email;
	private String password;
	// @NotBlank(message = "Username is mandatory")
	@NaturalId
	private String username;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ToString.Exclude
	private List<Role> roles;
}
