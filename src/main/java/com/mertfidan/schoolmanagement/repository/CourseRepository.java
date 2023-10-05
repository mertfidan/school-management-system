package com.mertfidan.schoolmanagement.repository;

import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByTeacherId(UUID teacherId);

}
