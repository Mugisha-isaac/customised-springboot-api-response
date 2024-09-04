package org.rw.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.rw.security.enums.EStudentDepartment;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class StudentDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private EStudentDepartment department;
}