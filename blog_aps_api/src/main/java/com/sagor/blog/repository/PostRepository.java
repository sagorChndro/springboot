package com.sagor.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post findBypostIdAndIsActiveTrue(Long postId);

	List<Post> findAllByIsActiveTrue();

}
