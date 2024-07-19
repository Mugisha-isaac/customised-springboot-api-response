package com.rw.studentMs.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private String error;
    private long timestamp;
    private String version;
    private String path;
    private T data;
}
