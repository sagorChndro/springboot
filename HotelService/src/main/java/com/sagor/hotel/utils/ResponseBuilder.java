package com.sagor.hotel.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.sagor.hotel.dto.ErrorResponseDto;
import com.sagor.hotel.dto.Response;

public final class ResponseBuilder {
	private ResponseBuilder() {

	}

	private static Set<ErrorResponseDto> getCustomErrors(BindingResult result) {
		Set<ErrorResponseDto> dtoSet = new HashSet<>();
		result.getFieldErrors().forEach(fieldError -> {
			ErrorResponseDto dto = ErrorResponseDto.builder().field(fieldError.getField())
					.message(fieldError.getDefaultMessage()).build();
			dtoSet.add(dto);
		});
		return dtoSet;
	}
	
	public static Response getFailureResponse(BindingResult result, String message) {
		return Response.builder()
				.message(message)
				.errors(getCustomErrors(result))
				.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.statuscode(HttpStatus.BAD_REQUEST.value())
				.timestamp(new Date().getTime())
				.build();
	}
	
	public static Response getFailureResponse(HttpStatus status, String message) {
		return Response.builder()
				.message(message)
				.status(status.getReasonPhrase())
				.statuscode(status.value())
				.timestamp(new Date().getTime())
				.build();
	}
	
	public static Response getSuccessResponse(HttpStatus status, String message, Object content) {
		return Response.builder()
				.message(message)
				.status(status.getReasonPhrase())
				.statuscode(status.value())
				.content(content)
				.timestamp(new Date().getTime())
				.build();
	}
	
	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement) {
		return Response.builder()
				.message(message)
				.status(status.getReasonPhrase())
				.statuscode(status.value())
				.content(content)
				.numberOfElement(numberOfElement)
				.timestamp(new Date().getTime())
				.build();
	}
	
	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement, long rowCount) {
		return Response.builder()
				.message(message)
				.status(status.getReasonPhrase())
				.statuscode(status.value())
				.content(content)
				.numberOfElement(numberOfElement)
				.rowCount(rowCount)
				.timestamp(new Date().getTime())
				.build();
	}

}
