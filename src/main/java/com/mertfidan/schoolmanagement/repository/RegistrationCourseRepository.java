package com.mertfidan.schoolmanagement.repository;

import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.RegistrationCourse;
import com.mertfidan.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegistrationCourseRepository extends JpaRepository<RegistrationCourse, UUID> {
    boolean existsByStudentAndCourse(Optional<Student> student, Optional<Course> course);
}
