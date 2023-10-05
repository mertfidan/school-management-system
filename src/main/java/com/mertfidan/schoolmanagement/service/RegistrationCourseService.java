package com.mertfidan.schoolmanagement.service;

import com.mertfidan.schoolmanagement.dto.RegistrationCourseDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface RegistrationCourseService {

    List<RegistrationCourseDto> createRegistrationByStudentId(UUID studentId, UUID courseId, RegistrationCourseDto registrationCourseRequest) throws NotFoundException;


    List<RegistrationCourseDto> getAllRegistrationsCourse();


    RegistrationCourseDto updateRegistrationCourse(UUID id, RegistrationCourseDto registrationCourseRequest) throws NotFoundException;

    String deleteRegistrationCourseById(UUID id) throws NotFoundException;
}
