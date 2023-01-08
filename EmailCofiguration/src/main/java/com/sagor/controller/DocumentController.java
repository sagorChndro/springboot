package com.sagor.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/{id}")
	public HttpEntity<byte[]> download(@PathVariable("id") Long id, HttpServletResponse response,
			HttpServletRequest request) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		Map<String, Object> resultMap = documentService.download(id);
		response.setHeader("Content-Disposition", "attachment; filename=" + resultMap.get("docName"));
		return new HttpEntity<byte[]>((byte[]) resultMap.get("bytes"), headers);
	}

	@GetMapping("/downloadJasper")
	public HttpEntity<byte[]> downloadJasper(HttpServletResponse response) {
		return documentService.downloadJasper(response);
	}

}
