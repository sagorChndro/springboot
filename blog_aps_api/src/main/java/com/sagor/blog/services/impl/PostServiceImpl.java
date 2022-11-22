package com.sagor.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.Post;
import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.CategoryRepository;
import com.sagor.blog.repository.PostRepository;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.PostService;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private String root = "Post";

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository,
			CategoryRepository categoryRepository) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Response createPost(PostDto postDto, Long userId, Long categoryId) {
		Post post = modelMapper.map(postDto, Post.class);
		post = postRepository.save(post);
		if (post != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");

	}

	@Override
	public Response updatePost(Long postId) {

		return null;
	}

	@Override
	public Response getPost(Long postId) {

		return null;
	}

	@Override
	public Response deletePost(Long postId) {

		return null;
	}

	@Override
	public Response getAllPost() {

		return null;
	}

	@Override
	public Response getPostsByCategory(Long categoryId) {

		return null;
	}

	@Override
	public Response getPostsByUser(Long userId) {

		return null;
	}

	@Override
	public Response searchPosts(String keyword) {

		return null;
	}

}
