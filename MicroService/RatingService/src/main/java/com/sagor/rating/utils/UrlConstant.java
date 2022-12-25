package com.sagor.rating.utils;

public final class UrlConstant {
	private UrlConstant() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public class RatingManagemant {
		public static final String ROOT = API + VERSION + "/ratings";
		public static final String CREATE_RATING = "/createRating";
		public static final String UPDATE_RATING = "/update/{ratingId}";
		public static final String DELETE_RATING = "/delete/{ratingId}";
		public static final String GET_RATING = "/get/{ratingId}";
		public static final String GET_ALL = "/all";
		public static final String GET_RATING_BY_USERID = "/users/{userId}";
		public static final String GET_RATING_BY_HOTELID = "/hotels/{hotelId}";
	}

}