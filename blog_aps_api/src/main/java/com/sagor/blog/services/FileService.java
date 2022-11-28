package com.sagor.blog.services;

import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;

public interface FileService {

	Response create(PostDto postDto, String docName, MultipartFile multipartFile);

}
