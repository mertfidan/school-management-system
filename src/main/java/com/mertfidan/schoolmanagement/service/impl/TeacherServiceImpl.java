package com.mertfidan.schoolmanagement.service.impl;

import com.mertfidan.schoolmanagement.dto.TeacherDto;
import com.mertfidan.schoolmanagement.exception.NotFoundException;
import com.mertfidan.schoolmanagement.model.Teacher;
import com.mertfidan.schoolmanagement.repository.TeacherRepository;
import com.mertfidan.schoolmanagement.service.TeacherService;
//import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

private final TeacherRepository teacherRepository;
private final ModelMapper modelMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {

        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);

        teacher.setCreatedAt(new Date());
        teacher.setCreatedBy("Admin");
        return modelMapper.map(teacherRepository.save(teacher),TeacherDto.class);

    }

    @Override
    public List<TeacherDto> getTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        //List<TeacherDto> teacherDtos = teachers.stream().map(teacher -> modelMapper.map(teacher,TeacherDto.class)).collect(Collectors.toList());
        return teachers.stream().map(teacher -> modelMapper.map(teacher,TeacherDto.class)).collect(Collectors.toList());
    }

    @Override
    public TeacherDto getTeacher(UUID id) throws NotFoundException {
      Optional<Teacher> teacher = teacherRepository.findById(id);
     /* if (teacher.isPresent()) {
          return modelMapper.map(teacher.get(),TeacherDto.class);
      }*/
        if (!teacher.isPresent()) {
            throw new NotFoundException("Teacher not found: " + id);

        }
        else {

            return teacher.map(value -> modelMapper.map(value, TeacherDto.class)).orElse(null);
        }
    }

    @Override
    public TeacherDto updateTeacher(UUID id, TeacherDto teacherDto) throws NotFoundException {
        Optional<Teacher> resultTeacher = teacherRepository.findById(id);




        if (!resultTeacher.isPresent()) {
            throw new NotFoundException("Teacher not found: " + id);

        }
        else {
            resultTeacher.get().setFirstName(teacherDto.getFirstName());
            resultTeacher.get().setLastName(teacherDto.getLastName());
            resultTeacher.get().setPhoneNumber(teacherDto.getPhoneNumber());
            resultTeacher.get().setEmail(teacherDto.getEmail());
            resultTeacher.get().setAddress(teacherDto.getAddress());
            resultTeacher.get().setUpdatedAt(new Date());
            resultTeacher.get().setUpdatedBy("Admin");
            return modelMapper.map(teacherRepository.save(resultTeacher.get()),TeacherDto.class);
        }

    }

    @Override
    public Boolean deleteTeacher(UUID id) throws NotFoundException {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (!teacher.isPresent()) {
            throw new NotFoundException("Teacher not found: " + id);

        }
        else {
            teacherRepository.deleteById(id);
            return true;
        }

    }

    @Override
    public List<Object[]> getAllSemester() {
        return teacherRepository.getAllSemester();
    }


    @Override
    public List<Object[]> getFallSemester() {
        return teacherRepository.getFallSemester();
    }

    @Override
    public List<Object[]> getSpringSemester() {
        return teacherRepository.getSpringSemester();
    }


    public Optional<Teacher> findById(UUID id) {

     return  teacherRepository.findById(id);
    }

    public boolean existsById(UUID id) {

      return !teacherRepository.existsById(id);
    }
}
