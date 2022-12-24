package com.sagor.rating.service;

import com.sagor.rating.dto.RatingDto;
import com.sagor.rating.dto.Response;

public interface RatingService {

	Response create(RatingDto ratingDto);

	Response update(RatingDto ratingDto, String ratingId);

	Response get(String ratingId);

	Response delete(String ratingId);

	Response getAll();

	// get all by userId
	Response getRatingByUserId(String userId);

	// get all by hotelId
	Response getRatingByHotelId(String hotelId);

}
