package com.sagor.service;

import org.springframework.web.multipart.MultipartFile;

import com.sagor.dto.Response;

public interface DocumentService {
	Response create(String docName, MultipartFile file);
}
