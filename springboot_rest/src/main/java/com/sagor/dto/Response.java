package com.sagor.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
	@JsonInclude(value = JsonInclude.Include.ALWAYS)
	private long timestamp;
	@JsonInclude(value = JsonInclude.Include.ALWAYS)
	private int statuscode;
	@JsonInclude(value = JsonInclude.Include.ALWAYS)
	private String status;
	@JsonInclude(value = JsonInclude.Include.ALWAYS)
	private String message;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Object content;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private int numberOfElement;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private long rowCount;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private List<ErrorResponseDto> errors;

}
