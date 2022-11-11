package com.sagor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByIdAndIsActiveTrue(Long id);

	List<Product> findALlByIsActiveTrue();

}
