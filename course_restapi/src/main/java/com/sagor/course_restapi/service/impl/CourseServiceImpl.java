package com.sagor.course_restapi.service.impl;

import com.sagor.course_restapi.dto.CourseDto;
import com.sagor.course_restapi.dto.Response;
import com.sagor.course_restapi.model.Course;
import com.sagor.course_restapi.repository.CourseRepository;
import com.sagor.course_restapi.service.CourseService;
import com.sagor.course_restapi.utils.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private String root = "Course";
    private final ModelMapper modelMapper;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response save(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        course = courseRepository.save(course);
        if(course != null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+" is created successfully", null);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, " Internal server error occur");
    }

    @Override
    public Response update(Long id, CourseDto courseDto) {
        Course course = courseRepository.findByIdAndIsActiveTrue(id);
        if(course != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(courseDto, course);
            course = courseRepository.save(course);
            if(course != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root+ " is updated successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" is not found");
    }

    @Override
    public Response delete(Long id) {
        Course course = courseRepository.findByIdAndIsActiveTrue(id);
        if(course != null){
            course.setIsActive(false);
            course = courseRepository.save(course);
            if(course != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, " Internal server error");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" is not found");
    }

    @Override
    public Response get(Long id) {
        Course course = courseRepository.findByIdAndIsActiveTrue(id);
        if(course != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            CourseDto courseDto = modelMapper.map(course, CourseDto.class);
            if(courseDto != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" is retireve successfully", courseDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" is not found");
    }

    @Override
    public Response getALl() {
        List<Course> courses = courseRepository.findByAllAndIsActiveTrue();
        List<CourseDto> courseDtos = this.getCourses(courses);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieve all successfully", courseDtos);
    }

    private List<CourseDto> getCourses(List<Course> courses){
        List<CourseDto> dtoList = new ArrayList<>();
        courses.forEach(course -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            CourseDto courseDto = modelMapper.map(course, CourseDto.class);
            dtoList.add(courseDto);
        });
        return dtoList;
    }
}
