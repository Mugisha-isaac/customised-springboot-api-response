package com.rw.studentMs.dto;

import com.rw.studentMs.enums.EStudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    private String name;
    private String email;
    private String phone;
    private EStudentDepartment department;
}
