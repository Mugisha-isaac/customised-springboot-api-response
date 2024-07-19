package com.rw.studentMs.dto;

import com.rw.studentMs.enums.EStudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentDto {
    private String name;
    private String email;
    private String phone;
    private EStudentDepartment department;
}
