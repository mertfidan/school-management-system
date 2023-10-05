package com.mertfidan.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;


//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="teachers")
//@Data
public class Teacher extends BaseEntity {

 /*   @Id
    @SequenceGenerator(name = "teacher_seq_gen",sequenceName = "teacher_gen",initialValue =100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teacher_seq_gen")
    @Column(name = "id")
    private Long id;
    */

    private String firstName ;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    //private Date dateOfBirth;

    @JsonBackReference
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;

//--------------------------------------

    @JsonBackReference
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;


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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
