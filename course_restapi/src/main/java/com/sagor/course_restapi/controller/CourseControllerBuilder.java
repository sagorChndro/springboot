package com.sagor.course_restapi.controller;

import com.sagor.course_restapi.service.CourseService;


public class CourseControllerBuilder {
    private CourseService courseService;

    public CourseControllerBuilder setCourseService(CourseService courseService) {
        this.courseService = courseService;
        return this;
    }

    public CourseController createCourseController() {
        return new CourseController(courseService);
    }
}