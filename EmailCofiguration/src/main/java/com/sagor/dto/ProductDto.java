package com.sagor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {

	private Long id;
	@NotEmpty(message = "name is mandatory")
	private String name;
	@NotNull(message = "price is mandatory")
	private Double price;
	private String description;

}
