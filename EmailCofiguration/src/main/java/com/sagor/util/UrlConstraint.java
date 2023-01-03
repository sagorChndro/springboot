package com.sagor.util;

public final class UrlConstraint {
	// constructor bind korte hobe jate eitar kew object create korte na pare
	private UrlConstraint() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public static class ProductManagement {
		public static final String ROOT = VERSION + API + "/product";
		public static final String CREATE = "/create";
		public static final String UPDATED = "/{id}";
		public static final String DELETED = "/{id}";
		public static final String GET = "/{id}";
		public static final String GET_ALL = "/all";
	}

}
