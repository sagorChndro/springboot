package com.sagor.userservice.utils;

public final class UrlConstant {
	private UrlConstant() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public class UserManagemant {
		public static final String ROOT = API + VERSION + "/users";
		public static final String CREATE_USER = "/createUser";
		public static final String UPDATE_USER = "/update/{userId}";
		public static final String DELETE_USER = "/delete/{userId}";
		public static final String GET_USER = "/get/{userId}";
		public static final String GET_ALL = "/all";
	}

}
