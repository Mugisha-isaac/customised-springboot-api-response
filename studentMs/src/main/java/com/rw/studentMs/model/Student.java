package com.rw.studentMs.model;

import com.rw.studentMs.enums.EStudentDepartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EStudentDepartment department;
}
