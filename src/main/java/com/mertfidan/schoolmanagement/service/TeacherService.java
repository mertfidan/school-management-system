package com.mertfidan.schoolmanagement.service;

import com.mertfidan.schoolmanagement.dto.TeacherDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    TeacherDto createTeacher (TeacherDto teacher);
    List<TeacherDto> getTeachers();

    TeacherDto getTeacher(UUID id) throws NotFoundException;
    TeacherDto updateTeacher(UUID id, TeacherDto teacher) throws NotFoundException;

    Boolean deleteTeacher(UUID id) throws NotFoundException;


    List<Object[]> getAllSemester();
    List<Object[]> getFallSemester();
    List<Object[]> getSpringSemester();
}
