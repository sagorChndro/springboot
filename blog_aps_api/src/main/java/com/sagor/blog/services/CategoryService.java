package com.sagor.blog.services;

import com.sagor.blog.payloadordto.CategoryDto;
import com.sagor.blog.payloadordto.Response;

public interface CategoryService {
	Response createCategory(CategoryDto categoryDto);

	Response updateCategory(Long categoryId, CategoryDto categoryDto);

	Response getCategory(Long categoryId);

	Response deleteCatgegory(Long categoryId);

	Response getCategories();
}
