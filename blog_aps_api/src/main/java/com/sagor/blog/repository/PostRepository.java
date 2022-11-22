package com.sagor.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Category;
import com.sagor.blog.model.Post;
import com.sagor.blog.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post save(User user);

	Post save(Category category);

}
