package com.sagor.blog.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.sagor.blog.payloadordto.ErrorResponseDto;
import com.sagor.blog.payloadordto.Response;

public final class ResponseBuilder {
	private ResponseBuilder() {

	}

	private static List<ErrorResponseDto> getCustomErrors(BindingResult result) {
		List<ErrorResponseDto> dtoList = new ArrayList<>();
		result.getFieldErrors().forEach(fieldError -> {
			ErrorResponseDto dto = ErrorResponseDto.builder().field(fieldError.getField())
					.message(fieldError.getDefaultMessage()).build();
			dtoList.add(dto);
		});
		return dtoList;
	}

	public static Response getFailureResponse(BindingResult result, String message) {
		return Response.builder().message(message).errors(getCustomErrors(result))
				.status(HttpStatus.BAD_REQUEST.getReasonPhrase()).statuscode(HttpStatus.BAD_REQUEST.value())
				.timestamp(new Date().getTime()).build();
	}

	public static Response getFailureResponse(HttpStatus status, String message) {
		return Response.builder().message(message).status(status.getReasonPhrase()).statuscode(status.value())
				.timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.statuscode(status.value()).timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.pageNumber(pageNumber).statuscode(status.value()).timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber,
			int pageSize) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.pageNumber(pageNumber).pageSize(pageSize).statuscode(status.value()).timestamp(new Date().getTime())
				.build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber,
			int pageSize, long totalPages) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.pageNumber(pageNumber).pageSize(pageSize).totalPages(totalPages).statuscode(status.value())
				.timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber,
			int pageSize, long totalPages, Boolean lastPage) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.pageNumber(pageNumber).pageSize(pageSize).totalPages(totalPages).lastPage(lastPage)
				.statuscode(status.value()).timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber,
			int pageSize, long totalPages, Boolean lastPage, int numberOfElement) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.numberOfElement(numberOfElement).pageNumber(pageNumber).pageSize(pageSize).totalPages(totalPages)
				.lastPage(lastPage).statuscode(status.value()).timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int pageNumber,
			int pageSize, long totalPages, Boolean lastPage, int numberOfElement, long rowCount) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.numberOfElement(numberOfElement).pageNumber(pageNumber).pageSize(pageSize).totalPages(totalPages)
				.totalPages(totalPages).statuscode(status.value()).rowCount(rowCount).timestamp(new Date().getTime())
				.build();
	}
}
