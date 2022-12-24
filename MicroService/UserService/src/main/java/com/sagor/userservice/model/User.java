package com.sagor.userservice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	private String userName;
	private String email;
	private String password;
	private String about;
	@Transient
	private List<Rating> ratings = new ArrayList<>();

}
