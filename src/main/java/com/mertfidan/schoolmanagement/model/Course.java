package com.mertfidan.schoolmanagement.model;

import com.fasterxml.jackson.annotation.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;


//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="courses")
//@Data
public class Course extends BaseEntity{


    private String courseCode;
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Teacher teacher;


    @Enumerated(EnumType.STRING)
    private Semester semester;


    @JsonBackReference
    @OneToMany(mappedBy = "course")
    private Set<RegistrationCourse> registrationCourses;


    public enum Semester {
        SPRING,
        FALL



   /*     @JsonValue
        public String getValue() {
            return value;
        }

        @JsonCreator
        public static Semester fromValue(String value) throws CustomBadRequestException, ValueInstantiationException {
            for (Semester semester : values()) {
                if (semester.value.equals(value)) {
                    return semester;
                }
            }
            throw new ValueInstantiationException("error ");
        }*/
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Set<RegistrationCourse> getRegistrationCourses() {
        return registrationCourses;
    }

    public void setRegistrationCourses(Set<RegistrationCourse> registrationCourses) {
        this.registrationCourses = registrationCourses;
    }




    
}
