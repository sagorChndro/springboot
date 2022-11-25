package com.sagor.blog.services;

import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;

public interface PostService {

	Response createPost(PostDto postDto, Long categoryId, Long userId);

	Response updatePost(PostDto postDto, Long postId);

	Response getPost(Long postId);

	Response deletePost(Long postId);

	Response getAllPostWithPageNoAndPageSize(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	Response getALlPost();

	// get all post by category
	Response getPostsByCategory(Long categoryId);

	// get all post by user
	Response getPostsByUser(Long userId);

	// search post
	Response searchPosts(String keywords);

}
