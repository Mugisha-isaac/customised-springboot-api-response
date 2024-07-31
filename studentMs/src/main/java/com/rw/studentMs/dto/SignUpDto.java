package com.rw.studentMs.dto;

import com.rw.studentMs.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role
) {
}
