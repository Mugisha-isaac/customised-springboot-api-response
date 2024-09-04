package org.rw.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rw.security.enums.EStudentDepartment;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDto {
    private String name;
    private String email;
    private String phone;
    private EStudentDepartment department;
}
