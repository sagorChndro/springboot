package com.sagor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String fullName;
	private String email;
	private String profile_picture;
	private String password;

}
