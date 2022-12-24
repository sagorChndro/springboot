package com.sagor.hotel.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.hotel.annotation.ApiController;
import com.sagor.hotel.dto.HotelDto;
import com.sagor.hotel.dto.Response;
import com.sagor.hotel.service.HotelService;
import com.sagor.hotel.utils.ResponseBuilder;
import com.sagor.hotel.utils.UrlConstant;

@ApiController
@RequestMapping(UrlConstant.HotelManagemant.ROOT)
public class HotelController {

	private final HotelService hotelService;

	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@PostMapping(UrlConstant.HotelManagemant.CREATE_HOTEL)
	public Response create(@RequestBody HotelDto hotelDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return hotelService.create(hotelDto);
	}

	@PutMapping(UrlConstant.HotelManagemant.UPDATE_HOTEL)
	public Response update(@RequestBody HotelDto hotelDto, @PathVariable("id") String id, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding result");
		}
		return hotelService.update(hotelDto, id);
	}

	@GetMapping(UrlConstant.HotelManagemant.GET_HOTEL)
	public Response get(@PathVariable("hotelId") String id) {
		return hotelService.get(id);
	}

	@DeleteMapping(UrlConstant.HotelManagemant.DELETE_HOTEL)
	public Response delete(@PathVariable("hotelId") String id) {
		return hotelService.delete(id);
	}

	@GetMapping(UrlConstant.HotelManagemant.GET_ALL)
	public Response getALl() {
		return hotelService.getAll();
	}

}
