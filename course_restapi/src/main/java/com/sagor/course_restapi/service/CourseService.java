package com.sagor.course_restapi.service;


import com.sagor.course_restapi.dto.CourseDto;
import com.sagor.course_restapi.dto.Response;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Response save(CourseDto courseDto);
    Response update(Long id, CourseDto courseDto);
    Response delete(Long id);
    Response get(Long id);
    Response getALl();
}
