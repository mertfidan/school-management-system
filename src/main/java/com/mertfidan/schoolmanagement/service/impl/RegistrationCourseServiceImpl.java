package com.mertfidan.schoolmanagement.service.impl;

import com.mertfidan.schoolmanagement.dto.RegistrationCourseDto;
import com.mertfidan.schoolmanagement.dto.TeacherDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.RegistrationCourse;
import com.mertfidan.schoolmanagement.model.Student;
import com.mertfidan.schoolmanagement.repository.RegistrationCourseRepository;
import com.mertfidan.schoolmanagement.service.RegistrationCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationCourseServiceImpl implements RegistrationCourseService {

    private final RegistrationCourseRepository registrationCourseRepository;
    private final StudentServiceImpl studentServiceImpl;
    private final CourseServiceImpl courseServiceImpl;

    private final ModelMapper modelMapper;
    public RegistrationCourseServiceImpl(RegistrationCourseRepository registrationCourseRepository, StudentServiceImpl studentServiceImpl, CourseServiceImpl courseServiceImpl, ModelMapper modelMapper) {
        this.registrationCourseRepository = registrationCourseRepository;
        this.studentServiceImpl = studentServiceImpl;
        this.courseServiceImpl = courseServiceImpl;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<RegistrationCourseDto> createRegistrationByStudentId(UUID studentId, UUID courseId, RegistrationCourseDto registrationCourseDto) throws NotFoundException {
        RegistrationCourse resultRegistrationCourse= modelMapper.map(registrationCourseDto, RegistrationCourse.class);
        Optional<Student> student = studentServiceImpl.findById(studentId);
        Optional<Course> course = courseServiceImpl.findById(courseId);
        boolean isAlreadyRegistered = registrationCourseRepository.existsByStudentAndCourse(student, course);

        if (!student.isPresent()) {
            throw new NotFoundException("Student not found with ID: " + studentId);
        }
        if (!course.isPresent()) {
            throw new NotFoundException("Course not found with ID: " + courseId);
        }

        if (isAlreadyRegistered){
            throw new NotFoundException("The registration already exists "+"Student id: "+studentId+ " Course id: "+courseId);
        }
        else {
            resultRegistrationCourse.setStudent(student.get());
            resultRegistrationCourse.setCourse(course.get());
            resultRegistrationCourse.setCreatedBy(student.get().getTeacher().getFirstName());
            resultRegistrationCourse.setCreatedAt(new Date());
        }




        //RegistrationCourse registrationCourse = registrationCourseRepository.save(registrationCourseRequest);
        
        //return modelMapper.map(studentRepository.save(studentResult), StudentDto.class);
        return Collections.singletonList(modelMapper.map(registrationCourseRepository.save(resultRegistrationCourse), RegistrationCourseDto.class));
        

    }

    @Override
    public List<RegistrationCourseDto> getAllRegistrationsCourse() {


        List<RegistrationCourse> registrationCourses = registrationCourseRepository.findAll();


        return registrationCourses.stream().map(registrationsCourse -> modelMapper.map(registrationsCourse,RegistrationCourseDto.class)).collect((Collectors.toList()));

       
    }

    public RegistrationCourseDto updateRegistrationCourse(UUID id, RegistrationCourseDto registrationCourseRequest) throws NotFoundException {
        Optional<RegistrationCourse> resultCourseRegistration = registrationCourseRepository.findById(id);

        if (!resultCourseRegistration.isPresent()){
            throw new NotFoundException("Registration not found: "+id);
        }
        else {
        String courseStudent=
                resultCourseRegistration.get().getStudent().getFirstName() +" "+ resultCourseRegistration.get().getStudent().getLastName();


            resultCourseRegistration.get().setRegistrationDescription(registrationCourseRequest.getRegistrationDescription());
            resultCourseRegistration.get().setUpdatedAt(new Date());
            resultCourseRegistration.get().setUpdatedBy(courseStudent);


            return modelMapper.map(registrationCourseRepository.save(resultCourseRegistration.get()), RegistrationCourseDto.class);

        }

    }

    @Override
    public String deleteRegistrationCourseById(UUID id) throws NotFoundException {
        Optional<RegistrationCourse> resultCourseRegistration = registrationCourseRepository.findById(id);
        if (!resultCourseRegistration.isPresent()){
          throw new NotFoundException("Registration not found: "+id);
        }
        else {
            registrationCourseRepository.deleteById(id);
            return resultCourseRegistration.get().getRegistrationDescription() + ": deleted";
        }

    }


}
