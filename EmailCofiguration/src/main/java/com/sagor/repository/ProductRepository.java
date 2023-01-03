package com.sagor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sagor.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByIdAndIsActiveTrue(Long id);

	List<Product> findAllByIsActiveTrue();
}
