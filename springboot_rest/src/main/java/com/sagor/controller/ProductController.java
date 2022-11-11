package com.sagor.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.annotation.ApiController;
import com.sagor.dto.ProductDto;
import com.sagor.dto.Response;
import com.sagor.service.ProductService;
import com.sagor.util.ResponseBuilder;
import com.sagor.util.UrlConstraint;

import lombok.RequiredArgsConstructor;

@ApiController
@RequestMapping(UrlConstraint.ProductManagement.ROOT)
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@PostMapping(UrlConstraint.ProductManagement.CREATE)
	public Response create(@Valid @RequestBody ProductDto produtDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return productService.save(produtDto);
	}

	@PutMapping(UrlConstraint.ProductManagement.UPDATE)
	public Response update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return productService.update(id, productDto);
	}

	@DeleteMapping(UrlConstraint.ProductManagement.DELETE)
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

}
