package com.rw.studentMs.config;

import com.rw.studentMs.interceptors.ApiResponseInterceptor;
import com.rw.studentMs.validation.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ApiResponseInterceptor apiResponseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiResponseInterceptor);
//        registry.addInterceptor(apiResponseInterceptor).addPathPatterns("/api/v1/student/**");
    }


    @Bean
    public StudentValidator studentValidator() {
        return new StudentValidator();
    }

}
