package com.sagor.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {
	private Long id;
	private Double price;
	private String description;
}
