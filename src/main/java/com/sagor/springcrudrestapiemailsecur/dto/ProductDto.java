package com.sagor.springcrudrestapiemailsecur.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class ProductDto {
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotNull(message = "price is mandatory")
    private Double price;
    private String description;
}
