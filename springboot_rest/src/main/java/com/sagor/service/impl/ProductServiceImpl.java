package com.sagor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;
import com.sagor.model.Product;
import com.sagor.repository.ProductRepository;
import com.sagor.service.ProductService;
import com.sagor.util.ResponseBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private String root = "Product";
	private final ModelMapper modelMapper;
	private final MailService mailService;

	@Override
	public Response save(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product = productRepository.save(product);
		if (product != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", null);
		}
		String to[] = { "taposh@abc.com" };
		mailService.sendNonHtmlMail(to, "Test mail", "Hello Taposh");
		return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occured");
	}

	@Override
	public Response update(Long id, ProductDto productDto) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(productDto, product);
			product = productRepository.save(product);
			if (product != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occured");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response get(Long id) {
		Product product = productRepository.findByIdAndIsActiveTrue(id);
		if (product != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			if (productDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retireve successfully", productDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error occured");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
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
					"Internal server error occured");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getAll() {
		List<Product> products = productRepository.findALlByIsActiveTrue();
		List<ProductDto> productDtos = this.getProducts(products);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retireve all successfully", productDtos);
	}

	private List<ProductDto> getProducts(List<Product> products) {
		List<ProductDto> productDtos = new ArrayList<>();
		products.forEach(product -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			productDtos.add(productDto);
		});
		return productDtos;
	}

}
