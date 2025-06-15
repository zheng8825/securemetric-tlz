package com.aliceshop.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class OnlineshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineshopApplication.class, args);
	}

}
