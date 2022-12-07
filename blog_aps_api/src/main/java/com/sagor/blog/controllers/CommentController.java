package com.sagor.blog.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.payloadordto.CommentDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.services.CommentService;
import com.sagor.blog.utils.ResponseBuilder;
import com.sagor.blog.utils.UrlConstraint;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@ApiController
@RequestMapping(UrlConstraint.CommentManagement.COMMENT_ROOT)
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping(UrlConstraint.CommentManagement.CREATE_COMMENT)
	public Response createComment(@RequestBody CommentDto commentDto, @PathVariable("userId") Long userId,
			@PathVariable("postId") Long postId, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding result");
		}
		return commentService.createComment(commentDto, userId, postId);

	}

	@DeleteMapping(UrlConstraint.CommentManagement.DELETE_COMMENT)
	public Response deleteComment(@PathVariable("commentId") Long commentId) {
		return commentService.deleteComment(commentId);
	}

}
