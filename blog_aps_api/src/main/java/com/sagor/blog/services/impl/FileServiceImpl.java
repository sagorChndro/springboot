package com.sagor.blog.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.model.Post;
import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.PostRepository;
import com.sagor.blog.services.FileService;
import com.sagor.blog.utils.DateUtils;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class FileServiceImpl implements FileService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	@Value("${project.image}")
	private String fileRootLocation;

	public FileServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response create(PostDto postDto, String docName, MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String location = getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyyy")
				+ File.separator + UUID.randomUUID().toString() + File.separator + fileName, fileName);
		if (multipartFile.isEmpty()) {
			return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "Document not found");
		}
		if (uploadFile(multipartFile, location)) {
			Post post = modelMapper.map(postDto, Post.class);
			post.setImageName(docName);
			post.setImageLocation(location);
			post = postRepository.save(post);
			if (post != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Document uploaded successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");

		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occue");
	}

	private Boolean uploadFile(MultipartFile multipartFile, String location) {
		try {
			byte[] bytes = multipartFile.getBytes();
			File file = new File(location.substring(0, location.lastIndexOf(File.separator)));
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = Paths.get(location + File.separator + multipartFile.getOriginalFilename());
			Files.write(path, bytes);
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	private String getUniqueLocation(String location, String fileName) {
		int existLocationCount = postRepository.countByimageLocation(location);
		if (existLocationCount == 0) {
			return location;
		}
		return getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyyy") + File.separator
				+ UUID.randomUUID().toString() + File.separator + fileName, fileName);
	}

}
