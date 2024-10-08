package com.rw.studentMs.service;

import com.rw.studentMs.dto.CreateStudentDto;
import com.rw.studentMs.model.Student;
import com.rw.studentMs.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createNewStudent(CreateStudentDto studentDto) throws BadRequestException {
        Student existingStudent = studentRepository.findStudentByEmail(studentDto.getEmail());
        if (existingStudent != null) {
            throw new BadRequestException("Student with email " + studentDto.getEmail() + " exists ");
        }
        Student newStudent = Student.builder()
                .email(studentDto.getEmail())
                .department(studentDto.getDepartment())
                .phone(studentDto.getPhone())
                .name(studentDto.getName())
                .build();
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(UUID studentId, CreateStudentDto studentDto) throws BadRequestException {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student with id " + studentId + " does not exist "));

        existingStudent.setDepartment(studentDto.getDepartment());
        existingStudent.setName(studentDto.getName());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setPhone(studentDto.getPhone());

        return studentRepository.save(existingStudent);
    }

    public Optional<Student> getStudentById(UUID studentId)  {
        return studentRepository.findById(studentId);
    }

    public Student deleteStudent(UUID studentId) throws BadRequestException {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student with id " + studentId + " does not exist "));
        studentRepository.deleteById(studentId);

        return existingStudent;
    }
}

