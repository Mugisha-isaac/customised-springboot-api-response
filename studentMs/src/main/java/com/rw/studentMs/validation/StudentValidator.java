package com.rw.studentMs.validation;

import com.rw.studentMs.dto.CreateStudentDto;
import com.rw.studentMs.model.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {
    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return CreateStudentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        CreateStudentDto student = (CreateStudentDto) target;

        if (student.getName() == null || student.getName().isEmpty()) {
            errors.rejectValue("name", "student.name.empty", "Name cannot be empty");
        }

        if(student.getName() != null && !student.getName().isEmpty() && student.getName().length() < 3) {
            errors.rejectValue("name", "student.name.length", "Name must be at least 3 characters long");
        }

        if (student.getEmail() == null || student.getEmail().isEmpty()) {
            errors.rejectValue("email", "student.email.empty", "Email cannot be empty");
        }

        if (student.getPhone() == null || student.getPhone().isEmpty()) {
            errors.rejectValue("phone", "student.phone.empty", "Phone cannot be empty");
        }

        if (student.getDepartment() == null) {
            errors.rejectValue("department", "student.department.empty", "Department cannot be empty");
        }
    }
}
