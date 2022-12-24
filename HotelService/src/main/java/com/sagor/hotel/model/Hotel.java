package com.sagor.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hotel extends BaseModel {
	@Id
	private String hotelId;
	private String name;
	private String location;
	private String about;

}
