package com.sagor.blog.services;

import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.payloadordto.Response;

public interface DocumentService {

	Response create(String docName, MultipartFile multipartFile);

}
