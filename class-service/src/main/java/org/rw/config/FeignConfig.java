package org.rw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.exception.BadRequestException;
import com.github.dockerjava.api.exception.InternalServerErrorException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.UnauthorizedException;
import com.squareup.okhttp.OkHttpClient;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rw.utils.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FeignConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();
//            if (servletRequestAttributes != null) {
//                String authorization = servletRequestAttributes.getRequest().getHeader(AUTHORIZATION_HEADER);
//                if (authorization != null) {
//                    requestTemplate.header(AUTHORIZATION_HEADER, authorization);
//                }
//            }
        };
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            String message;
            try {
                message = new ObjectMapper().readValue(response.body().asInputStream().readAllBytes(), ApiResponse.class).getMessage();
                System.out.println("Message............" + message);
            } catch (Exception e) {
                throw new InternalServerErrorException(e.getMessage());
            }

            return switch (response.status()) {
                case 400 -> new BadRequestException(message);
                case 401 -> new UnauthorizedException(message);
                case 404 -> new NotFoundException(message);
                case 500 -> new InternalServerErrorException(message);
                default -> new Exception(message);
            };
        };
    }
}
