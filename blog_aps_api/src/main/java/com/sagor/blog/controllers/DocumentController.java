package com.sagor.blog.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.services.DocumentService;

@ApiController
public class DocumentController {
	private final DocumentService documentService;

	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	@PostMapping("/documents/create")
	public Response create(@RequestParam(name = "docName") String docName,
			@RequestParam(name = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		return documentService.create(docName, file);
	}

}
