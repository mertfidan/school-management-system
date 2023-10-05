package com.mertfidan.schoolmanagement.dto;

//import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

//@Data
public class RegistrationCourseDto {


    private UUID id;
    @Size(min = 5,max = 255,message ="Lenght must be 5-80")
    @NotNull(message = "Must be not null")
    @Pattern(regexp = "^[a-zA-Z0-9/ ]+$")
    private String registrationDescription;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRegistrationDescription() {
        return registrationDescription;
    }

    public void setRegistrationDescription(String registrationDescription) {
        this.registrationDescription = registrationDescription;
    }
}
