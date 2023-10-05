package com.mertfidan.schoolmanagement.service.impl;

import com.mertfidan.schoolmanagement.dto.CourseDto;
import com.mertfidan.schoolmanagement.dto.StudentDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.model.Course;
import com.mertfidan.schoolmanagement.model.Student;
import com.mertfidan.schoolmanagement.model.Teacher;
import com.mertfidan.schoolmanagement.repository.StudentRepository;
import com.mertfidan.schoolmanagement.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherServiceImpl teacherServiceImpl;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, TeacherServiceImpl teacherServiceImpl, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.teacherServiceImpl = teacherServiceImpl;
        this.modelMapper = modelMapper;
    }


    @Override
    public StudentDto getStudent(UUID id) throws NotFoundException {
        //Student student = studentRepository.findById(id).orElseThrow(()-> new NotFoundException("Student not found: " + id));

        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new NotFoundException("Student not found: " + id);

        }
        else {

            return student.map(value -> modelMapper.map(value, StudentDto.class)).orElse(null);
        }
    }

    @Override
    public StudentDto updateStudent(UUID id, StudentDto student) throws NotFoundException {
        Optional<Student> resultStudent = studentRepository.findById(id);
        if (!resultStudent.isPresent()) {
            throw new NotFoundException("Student not found: " + id);

        }
        else {
            resultStudent.get().setFirstName(student.getFirstName());
            resultStudent.get().setLastName(student.getLastName());
            resultStudent.get().setEmail(student.getEmail());
            resultStudent.get().setAddress(student.getAddress());
            resultStudent.get().setUpdatedAt(new Date());
            resultStudent.get().setUpdatedBy(resultStudent.get().getTeacher().getFirstName());
            return modelMapper.map(studentRepository.save(resultStudent.get()),StudentDto.class);
        }
    }


   /* @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto,Student.class);
        student.setCreatedAt(new Date());
        student.setCreatedBy("Admin");
        return modelMapper.map(studentRepository.save(student),StudentDto.class);
    }*/
    @Override
    public StudentDto createStudentByTeacherId(UUID id, StudentDto studentDto) throws NotFoundException {
        Student studentResult= modelMapper.map(studentDto,Student.class);
        Optional<Teacher> teacher =  teacherServiceImpl.findById(id);
        if (!teacher.isPresent()) {
         throw new NotFoundException("Teacher not found: "+id);
        }
        else
        {
            studentResult.setTeacher(teacher.get());
            studentResult.setCreatedAt(new Date());
            studentResult.setCreatedBy(studentResult.getTeacher().getFirstName());

            return modelMapper.map(studentRepository.save(studentResult), StudentDto.class);
        }


    }



    @Override
    public List<StudentDto> getStudents() {

        List<Student> students = studentRepository.findAll();
        //List<StudentDto> studentDtos = students.stream().map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());
        return students.stream().map(student -> modelMapper.map(student,StudentDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<StudentDto> getStudentsByTeacherId(UUID teacherId) throws NotFoundException {

        Optional<Teacher> getTeacherId= teacherServiceImpl.findById(teacherId);
        List<Student> students = studentRepository.findByTeacherId(teacherId);
        if (!getTeacherId.isPresent()) {
            throw new NotFoundException("Teacher not found: " + teacherId);

        } else {

            return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).collect((Collectors.toList()));
        }

    }

    @Override
    public Boolean deleteStudent(UUID id) throws NotFoundException {
        Optional<Student>  student = studentRepository.findById(id);


        if (!student.isPresent()){
            throw new NotFoundException("Student not found: "+id);

        }
        else{ studentRepository.deleteById(id);
            return true;

        }
    }


    public Optional<Student> findById(UUID studentId) {

        return studentRepository.findById(studentId);
    }
}
