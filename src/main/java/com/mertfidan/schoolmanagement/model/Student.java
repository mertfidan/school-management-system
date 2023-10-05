package com.mertfidan.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
//@Data
public class Student extends BaseEntity{

    private String firstName;
    private String lastName;
    private String address;
    private String email;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Teacher teacher;

    ///-----------------------------------------------------




    @JsonBackReference
    @OneToMany(mappedBy = "student")
    private Set<RegistrationCourse> registrationCourses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<RegistrationCourse> getRegistrationCourses() {
        return registrationCourses;
    }

    public void setRegistrationCourses(Set<RegistrationCourse> registrationCourses) {
        this.registrationCourses = registrationCourses;
    }
}
