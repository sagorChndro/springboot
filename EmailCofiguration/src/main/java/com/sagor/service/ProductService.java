package com.sagor.service;

import org.springframework.http.HttpEntity;

import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;

public interface ProductService {

	Response save(ProductDto productDto);

	Response update(Long id, ProductDto productDto);

	Response delete(Long id);

	Response get(Long id);

	Response getAll();

	HttpEntity<byte[]> getPdfResponse();

}
