package com.aliceshop.onlineshop.controller;

import com.aliceshop.onlineshop.security.JwtService;
import com.aliceshop.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mobile")
public class MobileProductController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Map<String, Object>>> getMobileProducts(@RequestHeader("Authorization") String authHeader) {
        // 1. Validate JWT
        String token = authHeader.replace("Bearer ", "");
        if (!jwtService.isTokenValid(token, jwtService.extractUsername(token))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 2. Prepare simplified data
        List<Map<String, Object>> simplified = productService.findAllDecrypted().stream()
                .map(p -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("name", p.getName());
                    m.put("price", p.getPrice());
                    return m;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(simplified);
    }
}
