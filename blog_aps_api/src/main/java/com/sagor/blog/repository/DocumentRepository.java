package com.sagor.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagor.blog.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	Document findByDocumentIdAndIsActiveTrue(Long documentId);

	int countByDocumentLocation(String location);
}
