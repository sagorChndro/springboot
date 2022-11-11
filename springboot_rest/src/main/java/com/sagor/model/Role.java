package com.sagor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Role extends BaseModel {

	private String name;
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	List<User> users = new ArrayList<>();

}
