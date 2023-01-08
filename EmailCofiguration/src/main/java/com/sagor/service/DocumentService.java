package com.sagor.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.dto.Response;

public interface DocumentService {
	Response create(String docName, MultipartFile file);

	Map<String, Object> download(Long id);

	HttpEntity<byte[]> downloadJasper(HttpServletResponse response);
}
