package com.aliceshop.onlineshop.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @Column(length = 32, nullable = false, updatable = false)
    private String id;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().replace("-", ""); // 32-character hex UUID
        }
    }

    private String name;

    // encrypted price stored in DB
    @Column(name = "price")
    private String encryptedPrice;

    // decrypted price for frontend/API only
    @Transient
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedPrice() {
        return encryptedPrice;
    }

    public void setEncryptedPrice(String encryptedPrice) {
        this.encryptedPrice = encryptedPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
