package org.rw.config;

import lombok.RequiredArgsConstructor;
import org.rw.Interceptors.ApiResponseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ApiResponseInterceptor apiResponseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiResponseInterceptor);
    }

//    @Bean
//    public StudentValidator studentValidator() {
//        return new StudentValidator();
//    }

}

