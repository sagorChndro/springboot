package com.sagor.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.Comment;
import com.sagor.blog.model.Post;
import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.CommentDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.CommentRepository;
import com.sagor.blog.repository.PostRepository;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.CommentService;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper,
			PostRepository postRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Response createComment(CommentDto commentDto, Long userId, Long postId) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		comment.setUser(user);
		Post post = postRepository.findBypostIdAndIsActiveTrue(postId);
		comment.setPost(post);
		comment = commentRepository.save(comment);
		if (comment != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Comment created successfully", comment);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
	}

	@Override
	public Response deleteComment(Long commentId) {
		Comment comment = commentRepository.findByCommentId(commentId);
		if (comment != null) {
			commentRepository.delete(comment);
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Comment deleted successfully", null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
	}

}
