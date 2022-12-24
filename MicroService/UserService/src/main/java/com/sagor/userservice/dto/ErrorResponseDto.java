package com.sagor.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponseDto {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String field;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

}
