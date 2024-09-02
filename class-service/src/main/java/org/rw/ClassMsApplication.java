package org.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class ClassMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassMsApplication.class, args);
    }
}