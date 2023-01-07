package com.sagor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.customAnnotation.ApiController;
import com.sagor.dto.Response;
import com.sagor.service.DocumentService;

@ApiController
@RequestMapping("/document")
public class DocumentController {

	private final DocumentService documentService;

	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	@PostMapping("/create")
	public Response create(@RequestParam("docName") String docName,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request,
			HttpServletResponse response) {
		return documentService.create(docName, multipartFile);
	}

}
