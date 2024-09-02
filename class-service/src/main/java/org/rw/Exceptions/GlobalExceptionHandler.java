package org.rw.Exceptions;

import org.rw.utils.ApiResponse;
import org.rw.utils.FieldErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex, WebRequest request) {
        List<FieldErrorDetail> errorDetailList = new ArrayList<>();
        errorDetailList.add(FieldErrorDetail.builder()
                .field(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .rejectedValue(ex.getCause() != null ? ex.getCause().getMessage() : null)
                .build()
        );
        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error",
                errorDetailList,
                System.currentTimeMillis(),
                "1.0.0",
                request.getDescription(false),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

