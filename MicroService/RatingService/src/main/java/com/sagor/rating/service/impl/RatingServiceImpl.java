package com.sagor.rating.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.rating.dto.RatingDto;
import com.sagor.rating.dto.Response;
import com.sagor.rating.model.Rating;
import com.sagor.rating.repository.RatingRepository;
import com.sagor.rating.service.RatingService;
import com.sagor.rating.utils.ResponseBuilder;

@Service
public class RatingServiceImpl implements RatingService {
	private final RatingRepository ratingRepository;
	private final ModelMapper modelMapper;
	private String root = "Rating";

	public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
		this.ratingRepository = ratingRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response create(RatingDto ratingDto) {
		Rating rating = modelMapper.map(ratingDto, Rating.class);
		if (rating != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", rating);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
	}

	@Override
	public Response update(RatingDto ratingDto, String ratingId) {
		Rating rating = ratingRepository.findByRatingIdAndIsActiveTrue(ratingId);
		if (rating != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(ratingDto, rating);
			rating = ratingRepository.save(rating);
			if (rating != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", rating);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");

		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response get(String ratingId) {
		Rating rating = ratingRepository.findByRatingIdAndIsActiveTrue(ratingId);
		if (rating != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
			if (ratingDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retireve successfully", ratingDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response delete(String ratingId) {
		Rating rating = ratingRepository.findByRatingIdAndIsActiveTrue(ratingId);
		if (rating != null) {
			rating.setIsActive(false);
			rating = ratingRepository.save(rating);
			if (rating != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getAll() {
		Set<Rating> ratings = ratingRepository.findAllByIsActiveTrue();
		Set<RatingDto> ratingDtos = this.getRating(ratings);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retireve successfully", ratingDtos);
	}

	@Override
	public Response getRatingByUserId(String userId) {
		Set<Rating> ratings = ratingRepository.findByUserIdAndIsActiveTrue(userId);
		Set<RatingDto> ratingDtos = this.getRatingFromUserId(ratings);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All" + root + " retireve successfully from user",
				ratingDtos);
	}

	@Override
	public Response getRatingByHotelId(String hotelId) {
		Set<Rating> ratings = ratingRepository.findByHotelIdAndIsActiveTrue(hotelId);
		Set<RatingDto> ratingDtos = this.getRatingFromHotelId(ratings);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All" + root + " retireve successfully from hotel",
				ratingDtos);
	}

	private Set<RatingDto> getRating(Set<Rating> ratings) {
		Set<RatingDto> ratingDtos = new HashSet<>();
		ratings.forEach(rating -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			RatingDto ratingDto = modelMapper.map(ratings, RatingDto.class);
			ratingDtos.add(ratingDto);
		});
		return ratingDtos;
	}

	private Set<RatingDto> getRatingFromUserId(Set<Rating> ratings) {
		Set<RatingDto> ratingDtos = new HashSet<>();
		ratings.forEach(rating -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			RatingDto ratingDto = modelMapper.map(ratings, RatingDto.class);
			ratingDtos.add(ratingDto);
		});
		return ratingDtos;
	}

	private Set<RatingDto> getRatingFromHotelId(Set<Rating> ratings) {
		Set<RatingDto> ratingDtos = new HashSet<>();
		ratings.forEach(rating -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			RatingDto ratingDto = modelMapper.map(ratings, RatingDto.class);
			ratingDtos.add(ratingDto);
		});
		return ratingDtos;
	}

}
