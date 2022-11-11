package com.sagor.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import com.sagor.dto.ErrorResponseDto;
import com.sagor.dto.Response;

public final class ResponseBuilder {
	private ResponseBuilder() {

	}

	private static List<ErrorResponseDto> getCustomError(BindingResult result) {
		List<ErrorResponseDto> dtoList = new ArrayList<>();
		result.getFieldErrors().forEach(fieldError -> {
			ErrorResponseDto dto = ErrorResponseDto.builder().field(fieldError.getField())
					.message(fieldError.getDefaultMessage()).build();
			dtoList.add(dto);
		});
		return dtoList;
	}

	public static Response getFailureResponse(BindingResult result, String message) {
		return Response.builder().message(message).errors(getCustomError(result))
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

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.statuscode(status.value()).numberOfElement(numberOfElement).timestamp(new Date().getTime()).build();
	}

	public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement,
			long rowcount) {
		return Response.builder().message(message).status(status.getReasonPhrase()).content(content)
				.statuscode(status.value()).numberOfElement(numberOfElement).timestamp(new Date().getTime()).build();
	}

}
