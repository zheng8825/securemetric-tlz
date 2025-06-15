package com.aliceshop.onlineshop.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserVm {
    @Id
    @Column(length = 32, nullable = false, updatable = false)
    private String id;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().replace("-", "");
        }
    }

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // hashed

    @Column(nullable = false)
    private String role; // e.g., ROLE_ADMIN, ROLE_USER

    // Getters and Setters
    public String getId() { return id; }

    public void setUsername(String username) { this.username = username; }

    public String getUsername() { return username; }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
