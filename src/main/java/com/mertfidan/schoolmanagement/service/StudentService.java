package com.mertfidan.schoolmanagement.service;

import com.mertfidan.schoolmanagement.dto.StudentDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    //StudentDto createStudent (StudentDto student);
    List<StudentDto> getStudents();
    StudentDto getStudent(UUID id) throws NotFoundException;

    StudentDto updateStudent(UUID id, StudentDto student) throws NotFoundException;

    Boolean deleteStudent(UUID id) throws NotFoundException;

    List<StudentDto> getStudentsByTeacherId(UUID id) throws NotFoundException;
    StudentDto createStudentByTeacherId(UUID id, StudentDto student) throws NotFoundException;




}
