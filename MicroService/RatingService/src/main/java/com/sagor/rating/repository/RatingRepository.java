package com.sagor.rating.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.rating.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, String> {
	Rating findByRatingIdAndIsActiveTrue(String ratingId);

	Set<Rating> findAllByIsActiveTrue();

	Set<Rating> findByUserIdAndIsActiveTrue(String userId);

	Set<Rating> findByHotelIdAndIsActiveTrue(String hotelId);
}
