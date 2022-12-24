package com.sagor.rating.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.rating.annotation.ApiController;
import com.sagor.rating.dto.RatingDto;
import com.sagor.rating.dto.Response;
import com.sagor.rating.service.RatingService;
import com.sagor.rating.utils.ResponseBuilder;

@ApiController
@RequestMapping("/api/v1")
public class RatingController {
	private final RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@PostMapping("/createRating")
	public Response createRating(@RequestBody RatingDto ratingDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
		}
		return ratingService.create(ratingDto);
	}

	@PutMapping("/update/{ratingId}")
	public Response updateRating(@RequestBody RatingDto ratingDto, @PathVariable("ratingId") String ratingId,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
		}
		return ratingService.update(ratingDto, ratingId);
	}

	@GetMapping("/get/{ratingId}")
	public Response getRating(@PathVariable("ratingId") String ratingId) {
		return ratingService.get(ratingId);
	}

	@DeleteMapping("/delete/{ratingId}")
	public Response deleteRating(@PathVariable("ratingId") String ratingId) {
		return ratingService.delete(ratingId);
	}

	@GetMapping("/all")
	public Response getRating() {
		return ratingService.getAll();
	}

	@GetMapping("/users/{userId}")
	public Response getRatingByUserId(@PathVariable("userId") String userId) {
		return ratingService.getRatingByUserId(userId);
	}

	@GetMapping("/hotels/{hotelId}")
	public Response getRatingByHotelId(@PathVariable("hotelId") String hotelId) {
		return ratingService.getRatingByHotelId(hotelId);
	}

}
