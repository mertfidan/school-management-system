package com.mertfidan.schoolmanagement.repository;

import com.mertfidan.schoolmanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    Teacher findByfirstName(String firstName);

    @Query("SELECT " +
            "s.firstName AS studentFirstName, " +
            "s.lastName AS studentLastName, " +
            "c.semester, " +
            "rc.registrationDescription, " +
            "c.courseCode, " +
            "t.firstName AS teacherFirstName, " +
            "t.lastName AS teacherLastName " +
            "FROM Teacher t " +
            "JOIN Student s ON t.id = s.teacher.id " +
            "JOIN Course c ON c.teacher.id = t.id " +
            "JOIN RegistrationCourse rc ON rc.student.id = s.id")
    List<Object[]> getAllSemester();

    @Query("SELECT " +
            "s.firstName AS studentFirstName, " +
            "s.lastName AS studentLastName, " +
            "c.semester, " +
            "rc.registrationDescription, " +
            "c.courseCode, " +
            "t.firstName AS teacherFirstName, " +
            "t.lastName AS teacherLastName " +
            "FROM Teacher t " +
            "JOIN Student s ON t.id = s.teacher.id " +
            "JOIN Course c ON c.teacher.id = t.id " +
            "JOIN RegistrationCourse rc ON rc.student.id = s.id " +
            "WHERE c.semester = 'SPRING' ")
    List<Object[]> getSpringSemester();


    @Query("SELECT " +
            "s.firstName AS studentFirstName, " +
            "s.lastName AS studentLastName, " +
            "c.semester, " +
            "rc.registrationDescription, " +
            "c.courseCode, " +
            "t.firstName AS teacherFirstName, " +
            "t.lastName AS teacherLastName " +
            "FROM Teacher t " +
            "JOIN Student s ON t.id = s.teacher.id " +
            "JOIN Course c ON c.teacher.id = t.id " +
            "JOIN RegistrationCourse rc ON rc.student.id = s.id " +
            "WHERE c.semester = 'FALL' ")
    List<Object[]> getFallSemester();

}
