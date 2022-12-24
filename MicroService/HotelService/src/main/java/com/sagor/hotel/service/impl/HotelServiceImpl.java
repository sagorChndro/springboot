package com.sagor.hotel.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.hotel.dto.HotelDto;
import com.sagor.hotel.dto.Response;
import com.sagor.hotel.model.Hotel;
import com.sagor.hotel.repository.HotelRepository;
import com.sagor.hotel.service.HotelService;
import com.sagor.hotel.utils.ResponseBuilder;

@Service
public class HotelServiceImpl implements HotelService {
	private final HotelRepository hotelRepository;
	private final ModelMapper modelMapper;
	private String root = "Hotel";

	public HotelServiceImpl(HotelRepository hotelRepository, ModelMapper modelMapper) {
		this.hotelRepository = hotelRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response create(HotelDto hotelDto) {
		Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
		String randomHotelId = UUID.randomUUID().toString();
		hotel.setId(randomHotelId);
		hotel = hotelRepository.save(hotel);
		if (hotel != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", hotel);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
	}

	@Override
	public Response update(HotelDto hotelDto, String id) {
		Hotel hotel = hotelRepository.findByIdAndIsActiveTrue(id);
		if (hotel != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(hotelDto, hotel);
			hotel = hotelRepository.save(hotel);
			if (hotel != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", hotel);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response get(String id) {
		Hotel hotel = hotelRepository.findByIdAndIsActiveTrue(id);
		if (hotel != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
			if (hotelDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieve successfully", hotelDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response delete(String id) {
		Hotel hotel = hotelRepository.findByIdAndIsActiveTrue(id);
		if (hotel != null) {
			hotel.setIsActive(false);
			hotel = hotelRepository.save(hotel);
			if (hotel != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getAll() {
		Set<Hotel> hotels = hotelRepository.findAllByIsActiveTrue();
		Set<HotelDto> hotelDtos = this.getHotel(hotels);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retireve successfully", hotelDtos);
	}

	private Set<HotelDto> getHotel(Set<Hotel> hotels) {
		Set<HotelDto> hotelDtos = new HashSet<>();
		hotels.forEach(hotel -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
			hotelDtos.add(hotelDto);
		});
		return hotelDtos;
	}

}
