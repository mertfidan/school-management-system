package com.mertfidan.schoolmanagement.controller;

import com.mertfidan.schoolmanagement.dto.TeacherDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teachers")
@PreAuthorize("hasRole('ADMIN')")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService)
    {
        this.teacherService = teacherService;
    }



    @PostMapping
    public ResponseEntity<TeacherDto> createdTeacher(@Valid @RequestBody TeacherDto teacher) {
        TeacherDto resultTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(resultTeacher);
    }


    @GetMapping//("/getAllTeachers")
    public ResponseEntity<List<TeacherDto>> getTeachers(){
       List<TeacherDto> teachers = teacherService.getTeachers();
       return ResponseEntity.ok(teachers);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable("id") UUID id) throws NotFoundException {
        TeacherDto teacher = teacherService.getTeacher(id);
        return ResponseEntity.ok(teacher);

    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("id") UUID id ,@Valid @RequestBody TeacherDto teacher) throws NotFoundException {

        TeacherDto resultTeacher =  teacherService.updateTeacher(id,teacher);
    return ResponseEntity.ok(resultTeacher);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable("id") UUID id) throws NotFoundException {
        Boolean status = teacherService.deleteTeacher(id);

        return ResponseEntity.ok(status);

    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/semesters")
    public List<Object[]> getAllSemester() {
        return teacherService.getAllSemester();

    }
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/semesters/spring")
    public List<Object[]> getSpringSemester() {
        return teacherService.getSpringSemester();

    }
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/semesters/fall")
    public List<Object[]> getFallSemester() {
        return teacherService.getFallSemester();

    }



}
