package com.sagor.course_restapi.controller;

import com.sagor.course_restapi.annotation.ApiController;
import com.sagor.course_restapi.dto.CourseDto;
import com.sagor.course_restapi.dto.Response;
import com.sagor.course_restapi.service.CourseService;
import com.sagor.course_restapi.utils.ResponseBuilder;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/addCourse")
    public Response create(@RequestBody CourseDto courseDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseBuilder.getFailureResponse(result, " Bean binding error");
        }
        return courseService.save(courseDto);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable("id") Long id,@Valid @RequestBody CourseDto courseDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseBuilder.getFailureResponse(result, "Bean binding error");
        }
        return courseService.update(id,courseDto);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Long id){
        return courseService.get(id);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        return courseService.delete(id);
    }

    @GetMapping("/all")
    public Response getAll(){
        return courseService.getALl();
    }
}
