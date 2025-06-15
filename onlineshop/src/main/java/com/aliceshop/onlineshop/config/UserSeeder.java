package com.aliceshop.onlineshop.config;

import com.aliceshop.onlineshop.entity.UserVm;
import com.aliceshop.onlineshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserSeeder {

    @Bean
    public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findAll().isEmpty()) {
                UserVm admin = new UserVm();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");

                UserVm user = new UserVm();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRole("ROLE_USER");

                repo.save(admin);
                repo.save(user);
            }
        };
    }
}
