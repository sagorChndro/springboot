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
import com.sagor.rating.utils.UrlConstant;

@ApiController
@RequestMapping(UrlConstant.RatingManagemant.ROOT)
public class RatingController {
	private final RatingService ratingService;

	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@PostMapping(UrlConstant.RatingManagemant.CREATE_RATING)
	public Response createRating(@RequestBody RatingDto ratingDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
		}
		return ratingService.create(ratingDto);
	}

	@PutMapping(UrlConstant.RatingManagemant.UPDATE_RATING)
	public Response updateRating(@RequestBody RatingDto ratingDto, @PathVariable("ratingId") String ratingId,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean Binding error");
		}
		return ratingService.update(ratingDto, ratingId);
	}

	@GetMapping(UrlConstant.RatingManagemant.GET_RATING)
	public Response getRating(@PathVariable("ratingId") String ratingId) {
		return ratingService.get(ratingId);
	}

	@DeleteMapping(UrlConstant.RatingManagemant.DELETE_RATING)
	public Response deleteRating(@PathVariable("ratingId") String ratingId) {
		return ratingService.delete(ratingId);
	}

	@GetMapping(UrlConstant.RatingManagemant.GET_ALL)
	public Response getRating() {
		return ratingService.getAll();
	}

	@GetMapping(UrlConstant.RatingManagemant.GET_RATING_BY_USERID)
	public Response getRatingByUserId(@PathVariable("userId") String userId) {
		return ratingService.getRatingByUserId(userId);
	}

	@GetMapping(UrlConstant.RatingManagemant.GET_RATING_BY_HOTELID)
	public Response getRatingByHotelId(@PathVariable("hotelId") String hotelId) {
		return ratingService.getRatingByHotelId(hotelId);
	}

}
