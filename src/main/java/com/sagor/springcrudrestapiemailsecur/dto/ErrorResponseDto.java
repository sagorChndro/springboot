package com.sagor.springcrudrestapiemailsecur.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder // Bibhinno object er property gula bibhinno
// way te set korte pari er moddhe popular way hocce Builder way te set kora
public class ErrorResponseDto {
    // ei property gula Builder way te set korbo
    // jodi error hoy taholei shudu matro ei property gula kaj korbe tai NON_NULL
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
