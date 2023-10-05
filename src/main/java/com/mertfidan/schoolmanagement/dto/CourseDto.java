package com.mertfidan.schoolmanagement.dto;

import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.Teacher;
//import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

//@Data
public class CourseDto {

    private UUID id;

    @Size(min = 5,max = 40,message ="Lenght must be 5-80")
    @NotNull(message = "Must be not null")
    @Pattern(regexp = "^[a-zA-Z0-9/ ]+$")
    private String courseCode;

    @NotNull(message = "Must be not null")
    @Pattern(regexp = "^[a-zA-Z/ ]+$")
    @Size(min = 5,max = 80,message ="Lenght must be 5-80")
    private String courseName;


    @Enumerated(EnumType.STRING)
    private Course.Semester semester;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course.Semester getSemester() {
        return semester;
    }

    public void setSemester(Course.Semester semester) {
        this.semester = semester;
    }
}

