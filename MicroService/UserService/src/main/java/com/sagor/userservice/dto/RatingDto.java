package com.sagor.userservice.dto;

import com.sagor.userservice.model.Hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
	private Hotel hotel;

}
