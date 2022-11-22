package com.sagor.blog.payloadordto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Long categoryId;
	@NotBlank
	private String categoryTitle;
	@NotBlank
	private String categoryDescription;

}
