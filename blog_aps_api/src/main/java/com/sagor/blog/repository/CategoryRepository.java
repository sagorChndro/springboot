package com.sagor.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findBycategoryIdAndIsActiveTrue(Long categoryId);

	List<Category> findAllByIsActiveTrue();
}
