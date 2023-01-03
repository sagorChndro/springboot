package com.sagor.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.customAnnotation.ApiController;
import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;
import com.sagor.service.ProductService;
import com.sagor.util.ResponseBuilder;
import com.sagor.util.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.ProductManagement.ROOT)
public class ProductController {
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping(UrlConstraint.ProductManagement.CREATE)
	public Response create(@Valid @RequestBody ProductDto productDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return productService.save(productDto);
	}

	@PutMapping(UrlConstraint.ProductManagement.UPDATED)
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return productService.save(productDto);
	}

	@DeleteMapping(UrlConstraint.ProductManagement.DELETED)
	public Response delete(@PathVariable("id") Long id) {
		return productService.delete(id);
	}

	@GetMapping(UrlConstraint.ProductManagement.GET)
	public Response get(@PathVariable("id") Long id) {
		return productService.get(id);
	}

	@GetMapping(UrlConstraint.ProductManagement.GET_ALL)
	public Response getAll() {
		return productService.getAll();
	}

	public HttpEntity<byte[]> getPdf(HttpServletResponse response) {
		return productService.getPdfResponse(response);
	}
}
