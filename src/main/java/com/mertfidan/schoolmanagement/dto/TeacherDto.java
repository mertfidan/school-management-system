package com.mertfidan.schoolmanagement.dto;

//import lombok.Data;

import javax.validation.constraints.*;
import java.util.UUID;


//@Data
public class TeacherDto {

    private UUID id;

    @NotNull(message = "Must be not null")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Size(min = 3,max = 80,message ="Lenght must be 3-80")
    private String firstName ;

    @Size(min = 3,max = 80,message ="Lenght must be 3-80")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank(message = "Must be not blank")
    private String lastName;

    @Size(min = 10,max = 255,message ="Lenght must be 10-80")
    @NotNull(message = "Must be not null")
    @Pattern(regexp = "^[a-zA-Z0-9/ ]+$")
    private String address;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotNull(message = "Must be not null")
    private String email;

    @Pattern(regexp = "[0-9\\s]{12}")
    @NotNull(message = "Must be not null")
    private String phoneNumber;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
}