package com.sagor.hotel.utils;

public final class UrlConstant {
	private UrlConstant() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public class HotelManagemant {
		public static final String ROOT = API + VERSION + "/hotels";
		public static final String CREATE_HOTEL = "/createHotel";
		public static final String UPDATE_HOTEL = "/update/{id}";
		public static final String DELETE_HOTEL = "/delete/{id}";
		public static final String GET_HOTEL = "/get/{id}";
		public static final String GET_ALL = "/all";
	}

}