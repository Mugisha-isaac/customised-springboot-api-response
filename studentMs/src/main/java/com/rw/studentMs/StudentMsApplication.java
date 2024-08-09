package com.rw.studentMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StudentMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentMsApplication.class, args);
	}

}
