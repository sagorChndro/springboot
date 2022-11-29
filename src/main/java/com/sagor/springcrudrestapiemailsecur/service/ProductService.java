package com.sagor.springcrudrestapiemailsecur.service;

import com.sagor.springcrudrestapiemailsecur.dto.ProductDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;

public interface ProductService {
    Response create(ProductDto productDto);
    Response update(Long id, ProductDto productDto);
    Response delete(Long id);
    Response get(Long id);
    Response getAll();
}
