package com.rw.studentMs.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.studentMs.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

@Component
public class ApiResponseInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       if(response.isCommitted()){
           return;
       }
       int statusCode = response.getStatus();
       String message = statusCode >= 400 ? "Error" : "Success";
       String error = statusCode >= 400 ? (ex != null ? ex.getMessage() : "Internal Server Error") : null;

       Object responseBody = null;
        ApiResponse<Object> apiResponse = new ApiResponse<>(statusCode,message,error,System.currentTimeMillis(),"1.0.0",request.getRequestURI(),responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        writeResponse(response,jsonResponse);
    }

    private void writeResponse(HttpServletResponse response, String jsonResponse) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(jsonResponse);
        writer.flush();
    }
}
