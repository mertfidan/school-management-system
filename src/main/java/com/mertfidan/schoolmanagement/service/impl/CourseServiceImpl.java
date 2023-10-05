package com.mertfidan.schoolmanagement.service.impl;

import com.mertfidan.schoolmanagement.dto.CourseDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.Teacher;
import com.mertfidan.schoolmanagement.repository.CourseRepository;
import com.mertfidan.schoolmanagement.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherServiceImpl teacherServiceImpl;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherServiceImpl teacherServiceImpl, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.teacherServiceImpl = teacherServiceImpl;
        this.modelMapper = modelMapper;
    }


    @Override
    public CourseDto createCourseByTeacherId(UUID id, CourseDto courseDto, CourseDto semester) throws NotFoundException{
        Course course = modelMapper.map(courseDto,Course.class);
        Optional<Teacher> teacherOptional = teacherServiceImpl.findById(id);

            if (!teacherOptional.isPresent()) {
                throw new NotFoundException("Teacher not found: "+id);
            }else  {

                Teacher teacher = teacherOptional.get();
                course.setTeacher(teacher);
                course.setCreatedAt(new Date());
                course.setCreatedBy(teacher.getFirstName() + " " + teacher.getLastName());

                return modelMapper.map(courseRepository.save(course),CourseDto.class);

            }


    }



    @Override
    public List<CourseDto> getCoursesByTeacherId(UUID id) throws NotFoundException {

        Optional<Teacher> teacherOptional = teacherServiceImpl.findById(id);
        List<Course> courses = courseRepository.findByTeacherId(id);



        if (!teacherOptional.isPresent()) {
            throw new NotFoundException("Teacher not found: "+id);

        }else  {

            return courses.stream().map(course -> modelMapper.map(course,CourseDto.class)).collect((Collectors.toList()));
        }



    }

    @Override
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> modelMapper.map(course,CourseDto.class)).collect((Collectors.toList()));
    }

    @Override
    public CourseDto updateCoursebyId(UUID id, CourseDto courseDto) throws NotFoundException {
        Optional<Course> resultCourse = courseRepository.findById(id);
        if (!resultCourse.isPresent()){
            throw new NotFoundException("Course not found: "+id);
        }
        else {
            String courseTeacher=
                    resultCourse.get().getTeacher().getFirstName()+" "+
                            resultCourse.get().getTeacher().getLastName();

            resultCourse.get().setCourseCode(courseDto.getCourseCode());
            resultCourse.get().setCourseName(courseDto.getCourseName());
            resultCourse.get().setSemester(courseDto.getSemester());
            resultCourse.get().setUpdatedAt(new Date());
            resultCourse.get().setUpdatedBy(courseTeacher);


            return modelMapper.map( courseRepository.save(resultCourse.get()), CourseDto.class);
        }

    }

    @Override
    public String deleteCourseById(UUID courseId) throws NotFoundException {
        Optional<Course> resultCourse = courseRepository.findById(courseId);
        if (!resultCourse.isPresent()){
            throw new NotFoundException("Course not found: "+courseId);
        }
        else {
            courseRepository.deleteById(courseId);
            return "Course Name: " + resultCourse.get().getCourseName() + " - deleted";

        }
    }


    public Optional<Course> findById(UUID courseId) {

       return courseRepository.findById(courseId);
    }
}
