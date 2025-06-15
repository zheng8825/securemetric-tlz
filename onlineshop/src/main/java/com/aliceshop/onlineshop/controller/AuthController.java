package com.aliceshop.onlineshop.controller;

import com.aliceshop.onlineshop.entity.UserVm;
import com.aliceshop.onlineshop.repository.UserRepository;
import com.aliceshop.onlineshop.security.AuthRequest;
import com.aliceshop.onlineshop.security.AuthResponse;
import com.aliceshop.onlineshop.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        UserVm user = userRepository.findByUsername(authRequest.getUsername());

        if (user == null || !passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
