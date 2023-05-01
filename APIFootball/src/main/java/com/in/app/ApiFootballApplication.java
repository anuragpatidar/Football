package com.in.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.in")
//@EnableSwagger2
public class ApiFootballApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFootballApplication.class, args);
	}
}
