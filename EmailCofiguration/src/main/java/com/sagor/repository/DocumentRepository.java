package com.sagor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	Document findByIdAndIsActiveTrue(Long id);

	int countByDocumentLocation(String location);
}
