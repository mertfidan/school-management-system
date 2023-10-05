package com.mertfidan.schoolmanagement.service;

import com.mertfidan.schoolmanagement.dto.CourseDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    //Course createCourseByTeacherId(UUID id, Course course, Course.Semester semester);

    CourseDto createCourseByTeacherId(UUID id, CourseDto courseDto, CourseDto semester) throws NotFoundException;

    List<CourseDto> getCoursesByTeacherId(UUID id) throws NotFoundException;
    List<CourseDto> getCourses();

    CourseDto updateCoursebyId(UUID id, CourseDto courseDto) throws NotFoundException;

    String deleteCourseById(UUID id) throws NotFoundException;
}
