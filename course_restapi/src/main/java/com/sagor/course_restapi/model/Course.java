package com.sagor.course_restapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Course extends BaseModel{
    private Long courseId;
    private String courseName;
    private Double coursePrice;
    private String courseDescription;
}
