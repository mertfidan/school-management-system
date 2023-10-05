package com.mertfidan.schoolmanagement.controller;

import com.mertfidan.schoolmanagement.dto.RegistrationCourseDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.service.RegistrationCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/registrations")
@PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
public class RegistrationCourseController {

    private final RegistrationCourseService registrationCourseService;

    public RegistrationCourseController(RegistrationCourseService registrationCourseService) {
        this.registrationCourseService = registrationCourseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<List<RegistrationCourseDto>> createRegistration(@PathVariable(value = "studentId")UUID studentId, @PathVariable(value = "courseId") UUID courseId,@Valid @RequestBody RegistrationCourseDto registrationCourseRequest) throws NotFoundException {
        List<RegistrationCourseDto> registrationCourse = registrationCourseService.createRegistrationByStudentId(studentId, courseId,registrationCourseRequest);



        return ResponseEntity.ok(registrationCourse);

    }

    @GetMapping
    public ResponseEntity<List<RegistrationCourseDto>> getAllRegistrationCourse(){
        List<RegistrationCourseDto> registrationCourse = registrationCourseService.getAllRegistrationsCourse();

        return ResponseEntity.ok(registrationCourse);
    }
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationCourseDto> updateRegistrationCourse(@PathVariable(value = "id")UUID id,@Valid @RequestBody RegistrationCourseDto registrationCourseRequest) throws NotFoundException {
        RegistrationCourseDto registrationCourse= registrationCourseService.updateRegistrationCourse(id,registrationCourseRequest);


        return ResponseEntity.ok(registrationCourse);

    }
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistrationCourseById(@PathVariable("id") UUID id) throws NotFoundException {

        String resultCourseRegistration = registrationCourseService.deleteRegistrationCourseById(id);



        return ResponseEntity.ok(resultCourseRegistration);
    }
}
