package com.aliceshop.onlineshop.bean;

import com.aliceshop.onlineshop.security.SHA256PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256PasswordEncoder();
    }
}

