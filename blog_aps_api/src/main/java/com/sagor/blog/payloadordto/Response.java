package com.sagor.blog.payloadordto;

import java.util.List;

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
	private int pageNumber;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int pageSize;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private long totalPages;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean lastPage;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int numberOfElement;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private long rowCount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ErrorResponseDto> errors;

}
