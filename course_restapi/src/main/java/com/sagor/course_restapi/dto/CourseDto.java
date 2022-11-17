package com.sagor.course_restapi.dto;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CourseDto{
    private Long id;
    @NotEmpty(message = "Course name is mandatory")
    private String courseName;
    @NotNull(message = "Course price is mandatory")
    private Double coursePrice;
    private String courseDescription;
}
