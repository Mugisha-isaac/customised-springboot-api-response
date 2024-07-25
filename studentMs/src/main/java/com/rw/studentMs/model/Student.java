package com.rw.studentMs.model;

import com.rw.studentMs.enums.EStudentDepartment;
import com.rw.studentMs.validation.CustomValidation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_student")
@Builder
public class Student {
    @GeneratedValue(generator = "UUID")
    @Id
    private UUID id;
    @CustomValidation(message = "Name should contain only alphabets")
    private String name;
    @NotNull(message = "Email cannot be null")
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EStudentDepartment department;
}
