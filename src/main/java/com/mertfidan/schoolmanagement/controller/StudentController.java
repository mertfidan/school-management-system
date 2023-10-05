package com.mertfidan.schoolmanagement.controller;

import com.mertfidan.schoolmanagement.dto.StudentDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/students")
@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Get StudentByStudentId

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("id") UUID id) throws NotFoundException {
        StudentDto studentDto = studentService.getStudent(id);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") UUID id,@Valid @RequestBody StudentDto studentDto) throws NotFoundException {

        StudentDto resultStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(resultStudent);
    }

/*    @PostMapping("/createStudent")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) {
        StudentDto resultStudent = studentService.createStudent(student);
        return ResponseEntity.ok(resultStudent);
    }*/

    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<StudentDto> createStudent(@PathVariable(value = "teacherId") UUID teacherId,@Valid @RequestBody StudentDto studentRequest) throws NotFoundException {
        StudentDto student = studentService.createStudentByTeacherId(teacherId, studentRequest);


        return ResponseEntity.ok(student);
    }

    //Get All Students

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        List<StudentDto> students = studentService.getStudents();

        return ResponseEntity.ok(students);

    }

    //Get Students

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<StudentDto>> getAllStudentsByTeacherId(@PathVariable(value = "teacherId") UUID teacherId) throws NotFoundException {

        List<StudentDto> students = studentService.getStudentsByTeacherId(teacherId);
        return ResponseEntity.ok(students);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") UUID id) throws NotFoundException {
        Boolean status = studentService.deleteStudent(id);

        return ResponseEntity.ok(status);
    }



}