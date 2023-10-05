package com.mertfidan.schoolmanagement.repository;

import com.mertfidan.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByTeacherId(UUID teacherId);

    List<Student> deleteById(Student student);
}
