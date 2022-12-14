package com.sagor.blog.utils;

public final class UrlConstraint {
	private UrlConstraint() {

	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public class UserManagement {
		// public static final String USER = "/users";
		// public static final String USER_ID = "/{userId}";
		public static final String USER_ROOT = VERSION + API + "/users";
		public static final String CREATE_USER = "/userCreate";
		public static final String UPDATE_USER = "/{userId}";
		public static final String DELETE_USER = "/{userId}";
		public static final String GET_USER = "/{userId}";
		public static final String GET_ALL_USER = "/allUser";
		public static final Long NORMAL_USER = (long) 502;
		public static final Long ADMIN_USER = (long) 501;
	}

	public class CategoryManagement {
		public static final String CATEGORY_ROOT = VERSION + API + "/categories";
		public static final String CREATE_CATEGORY = "/categoriCreate";
		public static final String UPDATE_CATEGORY = "/{categoryId}";
		public static final String DELETE_CATEGORY = "/{categoryId}";
		public static final String GET_CATEGORY = "/{categoryId}";
		public static final String GET_ALL_CATEGORY = "/allCategory";
	}

	public class PostManagement {
		public static final String POST_ROOT = VERSION + API + "/posts";
		public static final String CREATE_POST = "/users/{userId}/categories/{categoryId}/createPost";
		public static final String UPDATE_POST = "/{postId}";
		public static final String DELETE_POST = "/{postId}";
		public static final String GET_POST = "/{postId}";
		public static final String GET_ALL_POST_BY_PAGE = "/allPostsByPage";
		public static final String GET_ALL_POST = "/allPosts";
		public static final String GET_POSTS_BY_USER = "/users/{userId}/posts";
		public static final String GET_POSTS_BY_CATEGORY = "/categories/{categoryId}/posts";
		public static final String SEARCH_POST = "search/{keywords}";
		public static final String PAGE_NUMBER = "0";
		public static final String PAGE_SIZE = "10";
		public static final String SORT_BY = "postId";
		public static final String SORT_DIR = "asc";
	}

	public class CommentManagement {
		public static final String COMMENT_ROOT = VERSION + API + "comments";
		public static final String CREATE_COMMENT = "/users/{userId}/posts/{postId}/createComment";
		public static final String DELETE_COMMENT = "/{commentId}";
	}
}
