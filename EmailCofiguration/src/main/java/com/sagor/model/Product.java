package com.sagor.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity
public class Product extends BaseModel {
	private Long id;
	private String name;
	private Double price;
	private String description;
	private LocalDateTime updateAt;

	@PreUpdate
	public void setPreUpdate() {
		this.updateAt = LocalDateTime.now();
	}
}
