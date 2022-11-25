package com.sagor.blog.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.services.PostService;
import com.sagor.blog.utils.ResponseBuilder;
import com.sagor.blog.utils.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.PostManagement.POST_ROOT)
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping(UrlConstraint.PostManagement.CREATE_POST)
	public Response createPost(@RequestBody PostDto postDto, @PathVariable("userId") Long userId,
			@PathVariable("categoryId") Long categoryId, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return postService.createPost(postDto, categoryId, userId);
	}

	@PutMapping(UrlConstraint.PostManagement.UPDATE_POST)
	public Response updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Long postId,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return postService.updatePost(postDto, postId);
	}

	@DeleteMapping(UrlConstraint.PostManagement.DELETE_POST)
	public Response deletePost(@PathVariable("postId") Long postId) {
		return postService.deletePost(postId);
	}

	@GetMapping(UrlConstraint.PostManagement.GET_POST)
	public Response getPost(@PathVariable("postId") Long postId) {
		return postService.getPost(postId);
	}

	@GetMapping(UrlConstraint.PostManagement.GET_ALL_POST)
	public Response getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,

			@RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize) {
		return postService.getAllPost(pageNumber, pageSize);
	}

	@GetMapping(UrlConstraint.PostManagement.GET_POSTS_BY_CATEGORY)
	public Response getPostsByCategory(@PathVariable("categoryId") Long categoryId) {
		return postService.getPostsByCategory(categoryId);
	}

	@GetMapping(UrlConstraint.PostManagement.GET_POSTS_BY_USER)
	public Response getPostsByUser(@PathVariable("userId") Long userId) {
		return postService.getPostsByUser(userId);
	}

}
