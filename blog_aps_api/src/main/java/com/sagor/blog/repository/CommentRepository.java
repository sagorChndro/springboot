package com.sagor.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment findByCommentId(Long commentId);

}
