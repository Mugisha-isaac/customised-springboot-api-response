package org.rw.clients;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.rw.config.FeignConfig;
import org.rw.security.dto.StudentDto;
import org.rw.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient( configuration = FeignConfig.class, value = "studentClient")
public interface StudentFeignClient {
    @GetMapping(value = "/api/v1/students/{studentId}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<StudentDto> getStudentById(@Valid @NotNull @PathVariable("studentId") UUID studentId);
}
