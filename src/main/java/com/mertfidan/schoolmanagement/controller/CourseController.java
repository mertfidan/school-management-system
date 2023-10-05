package com.mertfidan.schoolmanagement.controller;

import com.mertfidan.schoolmanagement.dto.CourseDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) { this.courseService = courseService;}


    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<CourseDto> createStudent(@PathVariable(value = "teacherId") UUID teacherId,@Valid @RequestBody CourseDto courseDto) throws NotFoundException {

           CourseDto resultCourseDto = courseService.createCourseByTeacherId(teacherId, courseDto, courseDto);

           return ResponseEntity.ok(resultCourseDto);


    }


    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseDto>> getCoursesByTeacherId(@PathVariable(value = "teacherId") UUID teacherId) throws NotFoundException {

        List<CourseDto> courses = courseService.getCoursesByTeacherId(teacherId);
        return ResponseEntity.ok(courses);
    }

    //Get All Courses
    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses(){
        List<CourseDto> courses = courseService.getCourses();

        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable("id")UUID id, @Valid @RequestBody CourseDto courseDto) throws NotFoundException {

        CourseDto resultCourse = courseService.updateCoursebyId(id,courseDto);

        return ResponseEntity.ok(resultCourse);

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCourse(@PathVariable("id") UUID id) throws NotFoundException {

        String resultCourse = courseService.deleteCourseById(id);


        return ResponseEntity.ok(resultCourse);
        }

    }







