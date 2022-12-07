package com.sagor.blog.services;

import com.sagor.blog.payloadordto.CommentDto;
import com.sagor.blog.payloadordto.Response;

public interface CommentService {

	Response createComment(CommentDto commentDto, Long userId, Long postId);

	Response deleteComment(Long commentId);

}
