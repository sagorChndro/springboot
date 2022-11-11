package com.sagor.service;

import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;

public interface ProductService {
	Response save(ProductDto productDto);

	Response update(Long id, ProductDto productDto);

	Response get(Long id);

	Response delete(Long id);

	Response getAll();

}
