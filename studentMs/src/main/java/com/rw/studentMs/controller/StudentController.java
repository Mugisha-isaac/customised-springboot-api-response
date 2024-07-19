package com.rw.studentMs.controller;

import com.rw.studentMs.dto.CreateStudentDto;
import com.rw.studentMs.model.Student;
import com.rw.studentMs.service.StudentService;
import com.rw.studentMs.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<Student>> getAllStudents(HttpServletRequest request) {
        List<Student> students = this.studentService.getAllStudents();
//        if (students.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(students, HttpStatus.OK);
        return new ApiResponse<>(
                200,
                "Success",
                null,
                System.currentTimeMillis(),
                "1.0.0",
                request.getRequestURI(),
                students
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Student> createStudent(
            @RequestBody CreateStudentDto newStudentRequest
    ) throws BadRequestException {
        Student savedStudent = studentService.createNewStudent(newStudentRequest);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Student> updateStudent(
            @PathVariable(name = "studentId") UUID studentId,
            @RequestBody CreateStudentDto updateStudentDto
    ) throws BadRequestException {
        Student updatedStudent = studentService.updateStudent(studentId, updateStudentDto);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Student> deleteStudent(
            @PathVariable(name = "studentId") UUID studentId
    ) throws BadRequestException {
        Student student = studentService.deleteStudent(studentId);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
