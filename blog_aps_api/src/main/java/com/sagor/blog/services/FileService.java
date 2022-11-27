package com.sagor.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;

public interface FileService {

	Response uploadImage(PostDto postDto, String path, MultipartFile file) throws IOException;

	InputStream getResource(String path, String fileName) throws FileNotFoundException;

}
