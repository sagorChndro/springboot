package com.sagor.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.dto.Response;
import com.sagor.model.Document;
import com.sagor.repository.DocumentRepository;
import com.sagor.service.DocumentService;
import com.sagor.util.DateUtils;
import com.sagor.util.ResponseBuilder;

@Service
public class DocuementServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	@Value("${file.root.location}")
	private String fileRootLocation;

	public DocuementServiceImpl(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Override
	public Response create(String docName, MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String location = getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyyy")
				+ File.separator + UUID.randomUUID().toString() + File.separator + fileName, fileName);
		if (multipartFile.isEmpty()) {
			return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "Document not found");
		}
		if (uploadFile(multipartFile, location)) {
			Document document = new Document();
			document.setDocumentName(docName);
			document.setDocumentLocation(location);
			document = documentRepository.save(document);
			if (document != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Document uploaded successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occurred");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occurred");
	}

	private Boolean uploadFile(MultipartFile multipartFile, String location) {
		try {
			byte[] bytes = multipartFile.getBytes();
			File file = new File(location);
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
		int existLocationCount = documentRepository.countByDocumentLocation(location);
		if (existLocationCount == 0) {
			return location;
		}
		return getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyy") + File.separator
				+ UUID.randomUUID().toString() + File.separator + fileName, fileName);
	}

}
