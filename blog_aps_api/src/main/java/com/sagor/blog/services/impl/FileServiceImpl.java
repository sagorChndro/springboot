package com.sagor.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.model.Post;
import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.PostRepository;
import com.sagor.blog.services.FileService;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class FileServiceImpl implements FileService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;

	public FileServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response uploadImage(PostDto postDto, String path, MultipartFile file) throws IOException {
		// file name
		String fileName = file.getOriginalFilename();

		// random name generate file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

		// full path
		String filePath = path + File.separator + fileName1;

		// create folder if does not exist folder
		File f = new File(path);

		if (!f.exists()) {
			f.mkdir();
		}

		// copy file
		Files.copy(file.getInputStream(), Paths.get(filePath));
		Post post = postRepository.findByimageName(fileName);
		post.setImageName(fileName);
		modelMapper.map(postDto, post);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Image uploaded successfully", fileName);
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		// db logi
		return is;
	}

}
