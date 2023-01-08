package com.sagor.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagor.dto.Response;
import com.sagor.model.Document;
import com.sagor.repository.DocumentRepository;
import com.sagor.service.DocumentService;
import com.sagor.util.DateUtils;
import com.sagor.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class DocuementServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	@Value("${file.root.location}")
	private String fileRootLocation;
	private final HikariDataSource dataSource;
//	@Value("classpath: src/main/resource/static/reports/product.jasper")
//	private final Resource resource;

	public DocuementServiceImpl(DocumentRepository documentRepository, HikariDataSource dataSource) {
		this.documentRepository = documentRepository;
		this.dataSource = dataSource;

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
			String dirLocation = location.substring(0, location.lastIndexOf(File.separator));
			File file = new File(dirLocation);
			if (!file.exists()) {
				file.mkdirs();
			}
			Path path = Paths.get(dirLocation + File.separator + multipartFile.getOriginalFilename());
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

	@Override
	public Map<String, Object> download(Long id) {
		Document document = documentRepository.findByIdAndIsActiveTrue(id);
		if (document == null) {
			return null;
		}
		// download
		File downloadableFile = new File(document.getDocumentLocation());
		if (!downloadableFile.exists()) {
			return null;
		}
		InputStream targetStream = null;
		try {
			targetStream = new FileInputStream(downloadableFile);
			byte[] bytes = IOUtils.toByteArray(targetStream);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("bytes", bytes);
			resultMap.put("docName", downloadableFile.getName());
			return resultMap;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public HttpEntity<byte[]> downloadJasper(HttpServletResponse response) {
		Map<String, Object> reportParams = new HashMap<>();
		try {
			JasperPrint print = JasperFillManager.fillReport(
					"C:\\Users\\Asus\\eclipse-workspace\\EmailCofiguration\\src\\main\\resources\\static\\reports\\product.jasper",
					reportParams, dataSource.getConnection());
			byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			response.setHeader("Content-Description", "attachment: filename=product_list.pdf");
			return new HttpEntity<>(pdfBytes, headers);
		} catch (JRException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
