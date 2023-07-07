package com.sagor.model;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String fullName;
	private String email;
	private String profile_picture;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Notification> notifications = new ArrayList<>();

}
