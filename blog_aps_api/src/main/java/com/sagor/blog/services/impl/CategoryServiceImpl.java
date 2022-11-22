package com.sagor.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.Category;
import com.sagor.blog.payloadordto.CategoryDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.CategoryRepository;
import com.sagor.blog.services.CategoryService;
import com.sagor.blog.utils.ResponseBuilder;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	private String root = "Category";

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		category = categoryRepository.save(category);
		if (category != null) {
			return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " created successfully", null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response updateCategory(Long categoryId, CategoryDto categoryDto) {
		Category category = categoryRepository.findBycategoryIdAndIsActiveTrue(categoryId);
		if (category != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(categoryDto, category);
			if (category != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response getCategory(Long categoryId) {
		Category category = categoryRepository.findBycategoryIdAndIsActiveTrue(categoryId);
		if (category != null) {
			CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
			if (categoryDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieve successfully", categoryDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response deleteCatgegory(Long categoryId) {
		Category category = categoryRepository.findBycategoryIdAndIsActiveTrue(categoryId);
		if (category != null) {
			category.setIsActive(false);
			category = categoryRepository.save(category);
			if (category != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internar Server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found for delete");
	}

	@Override
	public Response getCategories() {
		List<Category> categories = categoryRepository.findAllByIsActiveTrue();
		List<CategoryDto> categoryDtos = this.getAllCategory(categories);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retrieved successfully",
				categoryDtos);
	}

	private List<CategoryDto> getAllCategory(List<Category> categories) {
		List<CategoryDto> dtoList = new ArrayList<>();
		categories.forEach(categoriy -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			CategoryDto dto = modelMapper.map(categoriy, CategoryDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
