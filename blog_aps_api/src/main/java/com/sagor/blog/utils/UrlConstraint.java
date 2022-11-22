package com.sagor.blog.utils;

public final class UrlConstraint {
	private UrlConstraint() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public class UserManagement {
		public static final String USER = "/users";
		public static final String USER_POST = USER + "/{userId}";
		public static final String USER_ROOT = VERSION + API + USER;
		public static final String CREATE_USER = "/userCreate";
		public static final String UPDATE_USER = "/{userId}";
		public static final String DELETE_USER = "/{userId}";
		public static final String GET_USER = "/{userId}";
		public static final String GET_ALL_USER = "/allUser";
	}

	public class CategoryManagement {
		public static final String CATEGORY = "/categories";
		public static final String CATEGORY_POST = UserManagement.USER_POST + CATEGORY + "/{categoryId}";
		public static final String CATEGORY_ROOT = VERSION + API + CATEGORY;
		public static final String CREATE_CATEGORY = "/categoriCreate";
		public static final String UPDATE_CATEGORY = "/{categoryId}";
		public static final String DELETE_CATEGORY = "/{categoryId}";
		public static final String GET_CATEGORY = "/{categoryId}";
		public static final String GET_ALL_CATEGORY = "/allCategory";
	}

	public class PostManagement {
		public static final String POST_ROOT = VERSION + API + "/posts";
		public static final String CREATE_POST = CategoryManagement.CATEGORY_POST + "/createPost";
		public static final String UPDATE_POST = "/{postId}";
		public static final String DELETE_POST = "/{postId}";
		public static final String GET_POST = "/{postId}";
		public static final String GET_ALL_POST = "/allPosts";
	}
}
