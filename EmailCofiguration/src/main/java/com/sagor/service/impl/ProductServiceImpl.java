package com.sagor.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;
import com.sagor.model.Product;
import com.sagor.repository.ProductRepository;
import com.sagor.service.ProductService;
import com.sagor.util.ResponseBuilder;
import com.zaxxer.hikari.HikariDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final String root = "Product";
	private final MailService mailService;
	private final HikariDataSource dataSource;
	private final ModelMapper modelMapper;
	@Value("classpath:src/main/resource/static/reports/product.jasper")
	private Resource reportResource;

	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, MailService mailService,
			HikariDataSource dataSource) {
		this.productRepository = productRepository;
		this.dataSource = dataSource;
		this.modelMapper = modelMapper;
		this.mailService = mailService;
	}

	@Override
	public Response save(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product = productRepository.save(product);
		if (product != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " is created", null);
		}
		String to[] = { "Bikash@23gmail.com" };
		mailService.sendNonHtmlMail(to, "Test Mail", "Hello Bikash");
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurd");
	}

	@Override
	public Response update(Long id, ProductDto productDto) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()); // this is model mapper
																							// configure null gula k
																							// shey avoid korbe
			modelMapper.map(productDto, product);
			product = productRepository.save(product);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " Updated Successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					" Internal Server Error occured");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " is not found");
	}

	@Override
	public Response delete(Long id) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			product.setIsActive(false);
			product = productRepository.save(product);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal Server Occured Error");
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " is not found");
	}

	@Override
	public Response get(Long id) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			if (productDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " Retrieve Successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " is not found");
	}

	@Override
	public Response getAll() {
		List<Product> products = productRepository.findAllByIsActiveTrue();
		List<ProductDto> productDtos = this.getProduct(products);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " Retrieve all successfully", productDtos);
	}

	private List<ProductDto> getProduct(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<>();
		products.forEach(product -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto dto = modelMapper.map(product, ProductDto.class);
			productDtos.add(dto);
		});
		return productDtos;
	}

	@Override
	public HttpEntity<byte[]> getPdfResponse(HttpServletResponse response) {
		Map<String, Object> reportParams = new HashMap<>();
		try {
			JasperPrint print = JasperFillManager.fillReport(reportResource.getFile().getName(), reportParams,
					dataSource.getConnection());
			byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			response.setHeader("Content-Description", "attachment: filename=abc.pdf");
			return new HttpEntity<>(pdfBytes, headers);
		} catch (JRException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

}
