package com.rw.studentMs.controller;

import com.rw.studentMs.dto.CreateStudentDto;
import com.rw.studentMs.model.Student;
import com.rw.studentMs.service.StudentService;
import com.rw.studentMs.utils.ApiResponse;
import com.rw.studentMs.utils.FieldErrorDetail;
import com.rw.studentMs.validation.StudentValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

    private final StudentService studentService;
    private final StudentValidator studentValidator;
    private final MessageSource MessageSource;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Student>> getAllStudents(HttpServletRequest request, Locale locale) {
        List<Student> students = this.studentService.getAllStudents();

        return new ApiResponse<>(
                200,
                MessageSource.getMessage("success", null, locale),
                null,
                System.currentTimeMillis(),
                "1.0.0",
                request.getRequestURI(),
                students
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Student> createStudent(
            @Valid @RequestBody CreateStudentDto newStudentRequest, HttpServletRequest request
    ) throws BadRequestException {

        Errors errors = new BeanPropertyBindingResult(newStudentRequest, "newStudentRequest");
        studentValidator.validate(newStudentRequest, errors);

        if (errors.hasErrors()) {
            List<FieldErrorDetail> errorDetails = errors.getFieldErrors().stream()
                    .map(error -> FieldErrorDetail.builder()
                            .field(error.getField())
                            .rejectedValue(Objects.requireNonNull(error.getRejectedValue()).toString())
                            .message(error.getDefaultMessage())
                            .build())
                    .toList();

            return new ApiResponse<>(
                    400,
                    "Validation Failed",
                    errorDetails,
                    System.currentTimeMillis(),
                    "1.0.0",
                    request.getRequestURI(),
                    null
            );
        }
        Student savedStudent = studentService.createNewStudent(newStudentRequest);

        return new ApiResponse<>(
                201,
                "Success",
                null,
                System.currentTimeMillis(),
                "1.0.0",
                request.getRequestURI(),
                savedStudent
        );
    }

    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> updateStudent(
            @PathVariable(name = "studentId") UUID studentId,
            @RequestBody CreateStudentDto updateStudentDto
    ) throws BadRequestException {
        Student updatedStudent = studentService.updateStudent(studentId, updateStudentDto);
        if (updatedStudent != null) {
            return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "students", key = "#id")
    public ApiResponse<Student> getStudentById(
            @PathVariable(name = "studentId") UUID studentId
    ) {
        Optional<Student> student = studentService.getStudentById(studentId);
        return student.map(value -> new ApiResponse<>(
                200,
                "Success",
                null,
                System.currentTimeMillis(),
                "1.0.0",
                "/api/v1/student/" + studentId,
                value
        )).orElseGet(() -> new ApiResponse<>(
                404,
                "Not Found",
                List.of(FieldErrorDetail.builder()
                        .field("studentId")
                        .rejectedValue(studentId.toString())
                        .message("Student with id " + studentId + " does not exist")
                        .build()),
                System.currentTimeMillis(),
                "1.0.0",
                "/api/v1/student/" + studentId,
                null
        ));

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
