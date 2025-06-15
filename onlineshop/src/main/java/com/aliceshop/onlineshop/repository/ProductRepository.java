package com.aliceshop.onlineshop.repository;

import com.aliceshop.onlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
