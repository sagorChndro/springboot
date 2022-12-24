package com.sagor.hotel.service;

import com.sagor.hotel.dto.HotelDto;
import com.sagor.hotel.dto.Response;

public interface HotelService {

	Response create(HotelDto hotelDto);

	Response update(HotelDto hotelDto, String id);

	Response get(String id);

	Response delete(String id);

	Response getAll();
}
