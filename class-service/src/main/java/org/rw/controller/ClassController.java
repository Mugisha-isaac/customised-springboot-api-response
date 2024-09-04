package org.rw.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.rw.dto.CreateClassDto;
import org.rw.model.MClass;
import org.rw.service.ClassService;
import org.rw.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<MClass>> getAllClasses(HttpServletRequest request) {
        List<MClass> classes = this.classService.getAllClasses();

        return new ApiResponse<>(
            200,
            "success",
            null,
            System.currentTimeMillis(),
            "1.0.0",
            request.getRequestURI(),
            classes
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MClass> createClass(@RequestBody CreateClassDto newClassRequest, HttpServletRequest request) {
        MClass newClass = this.classService.createNewClass(newClassRequest);

        return new ApiResponse<>(
            201,
            "success",
            null,
            System.currentTimeMillis(),
            "1.0.0",
            request.getRequestURI(),
            newClass
        );
    }

    @PostMapping("/enroll/{classId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> enrollStudent(@PathVariable("classId") UUID classId, @RequestParam(name = "studentId") UUID studentId, HttpServletRequest request) {
        this.classService.enrollStudent(classId, studentId);

        return new ApiResponse<>(
            201,
            "success",
            null,
            System.currentTimeMillis(),
            "1.0.0",
            request.getRequestURI(),
            null
        );
    }
}
