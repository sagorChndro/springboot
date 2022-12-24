package com.sagor.userservice.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Response {
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private long timestamp;
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String status;
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String message;
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private int statuscode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object content;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int numberOfElement;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private long rowCount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Set<ErrorResponseDto> errors;

}
