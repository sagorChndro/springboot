package com.sagor.blog.controllers;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sagor.blog.annotations.ApiController;
import com.sagor.blog.payloadordto.CategoryDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.services.CategoryService;
import com.sagor.blog.utils.ResponseBuilder;
import com.sagor.blog.utils.UrlConstraint;

@ApiController
@RequestMapping(UrlConstraint.CategoryManagement.CATEGORY_ROOT)
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping(UrlConstraint.CategoryManagement.CREATE_CATEGORY)
	public Response createCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding error");
		}
		return categoryService.createCategory(categoryDto);
	}

	@PutMapping(UrlConstraint.CategoryManagement.UPDATE_CATEGORY)
	public Response updateCategory(@PathVariable("categoryId") Long categoryId,
			@Valid @RequestBody CategoryDto categoryDto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseBuilder.getFailureResponse(result, "Bean binding result");
		}
		return categoryService.updateCategory(categoryId, categoryDto);
	}

	@GetMapping(UrlConstraint.CategoryManagement.GET_CATEGORY)
	public Response getCategory(@PathVariable("categoryId") Long categoryId) {
		return categoryService.getCategory(categoryId);
	}

	@DeleteMapping(UrlConstraint.CategoryManagement.DELETE_CATEGORY)
	public Response deleteCatgegory(@PathVariable("categoryId") Long categoryId) {
		return categoryService.deleteCatgegory(categoryId);
	}

	@GetMapping(UrlConstraint.CategoryManagement.GET_ALL_CATEGORY)
	public Response getCategories() {
		return categoryService.getCategories();
	}

}
