package com.sagor.springcrudrestapiemailsecur.util;

import com.sagor.springcrudrestapiemailsecur.dto.ErrorResponseDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;
import org.aspectj.bridge.IMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//ResponseBuilder Class final hobe
public final class ResponseBuilder {
    private ResponseBuilder(){}// jate newly object create na hoy

    // Error dui dhoroner hote pare Data Validation abong Bean Validation
    // shongkranto error hote pare jodi Bean Validation shongkranto error hoy
    // tahole seitar jonno CustomDto(ErrorResponseDto) make korbo
    // Bean Validation er jonno amader BindingResult nite hobe ja error contain kore
    private static List<ErrorResponseDto> getCustomErrors(BindingResult result){
        List<ErrorResponseDto> dtoList = new ArrayList<>();
        // error Build korbo
        result.getFieldErrors().forEach(fieldError -> {
            // field error ashbe ErrorResponseDto theke tai er ekta object make korbo and
            //build korbo
            ErrorResponseDto dto = ErrorResponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage()).build();
            dtoList.add(dto);
        });
        return dtoList;

    }

    public static Response getFailureResponse(BindingResult result, String message){
        return Response.builder()
                .message(message)
                .errors(getCustomErrors(result))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statuscode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime()).build();
    }

    public static Response getFailureResponse(HttpStatus status, String message){
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statuscode(status.value())
                .timestamp(new Date().getTime()).build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content){
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statuscode(status.value())
                .timestamp(new Date().getTime()).build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement){
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .numberOfElement(numberOfElement)
                .statuscode(status.value())
                .timestamp(new Date().getTime()).build();
    }
    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement, long rowCount){
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .numberOfElement(numberOfElement)
                .statuscode(status.value())
                .rowCount(rowCount)
                .timestamp(new Date().getTime())
                .build();
    }
}
