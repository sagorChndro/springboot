package com.sagor.hotel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
	private String id;
	@NotBlank(message = "Hotel name mandatory")
	private String name;
	@NotNull(message = "Location must be mandatory")
	private String location;
	@NotBlank(message = "About must not be empty")
	private String about;
}
