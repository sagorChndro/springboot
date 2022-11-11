package com.sagor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String field;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String message;

}
