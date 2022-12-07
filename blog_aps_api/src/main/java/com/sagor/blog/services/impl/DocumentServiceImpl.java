package com.sagor.blog.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.blog.model.Document;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.DocumentRepository;
import com.sagor.blog.services.DocumentService;
import com.sagor.blog.utils.DateUtils;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class DocumentServiceImpl implements DocumentService {
	private final DocumentRepository documentRepository;
	@Value("${project.image}")
	private String fileRootLocation;

	public DocumentServiceImpl(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Override
	public Response create(String docName, MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		String location = getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyyy")
				+ File.separator + UUID.randomUUID().toString() + File.separator + fileName, fileName);
		if (uploadFile(multipartFile, location)) {
			Document document = new Document();
			document.setDocumentName(docName);
			document.setDocumentLocation(location);
			document = documentRepository.save(document);
			if (document != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Document uploaded successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server occure");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server occure");
	}

	private Boolean uploadFile(MultipartFile multipartFile, String location) {
		try {
			byte[] bytes = multipartFile.getBytes();
			File file = new File(location.substring(0, location.lastIndexOf(".")));
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = Paths.get(location + File.separator + multipartFile.getOriginalFilename());
			Files.write(path, bytes);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	private String getUniqueLocation(String location, String fileName) {
		int existCountLocation = documentRepository.countByDocumentLocation(location);
		if (existCountLocation == 0) {
			return location;
		}
		return getUniqueLocation(fileRootLocation + DateUtils.getStringDate(new Date(), "dd_MM_yyyy") + File.separator
				+ UUID.randomUUID().toString() + File.separator + fileName, fileName);
	}

}
