package com.sagor.hotel.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
	Hotel findByIdAndIsActiveTrue(String id);

	Set<Hotel> findAllByIsActiveTrue();
}
