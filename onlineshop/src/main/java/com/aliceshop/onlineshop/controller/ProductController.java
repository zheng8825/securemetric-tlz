package com.aliceshop.onlineshop.controller;

import com.aliceshop.onlineshop.dto.ApiResponse;
import com.aliceshop.onlineshop.entity.Product;
import com.aliceshop.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products with decrypted prices
    @GetMapping("/all")
    public ApiResponse<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllDecrypted();
        return new ApiResponse<>(true, "Product list retrieved successfully", products);
    }

    // Add new product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ApiResponse<String> addProduct(@RequestBody Product product) throws Exception {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            return new ApiResponse<>(false, "Product name and price must not be null", null);
        }
        productService.saveProduct(product);
        return new ApiResponse<>(true, "Product added successfully", "success");
    }

    // Update product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ApiResponse<String> updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
        if (product == null || product.getName() == null || product.getPrice() == null) {
            return new ApiResponse<>(false, "Product name and price must not be null", null);
        }
        Product existing = productService.findById(id);
        if (existing == null) {
            return new ApiResponse<>(false, "Product not found", null);
        }
        existing.setName(product.getName());
        existing.setPrice(product.getPrice()); // internally encrypts
        productService.updateProduct(existing);
        return new ApiResponse<>(true, "Product updated successfully", "success");
    }

    // Delete product
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        if (id == null) {
            return new ApiResponse<>(false, "Product id must not be null", null);
        }
        productService.deleteProduct(id);
        return new ApiResponse<>(true, "Product deleted successfully", null);
    }
}
