package com.aliceshop.onlineshop.service;

import com.aliceshop.onlineshop.entity.Product;
import com.aliceshop.onlineshop.repository.ProductRepository;
import com.aliceshop.onlineshop.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product) throws Exception {
        product.setEncryptedPrice(EncryptionUtil.encrypt(product.getPrice().toString()));
        productRepository.save(product);
    }

    public void updateProduct(Product product) throws Exception {
        product.setEncryptedPrice(EncryptionUtil.encrypt(product.getPrice().toString()));
        productRepository.save(product);
    }


    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllDecrypted() {
        return productRepository.findAll().stream()
                .map(product -> {
                    try {
                        BigDecimal decrypted = EncryptionUtil.decrypt(product.getEncryptedPrice());
                        product.setPrice(decrypted);
                        product.setEncryptedPrice("");
                        return product;
                    } catch (Exception e) {
                        return product; // or handle properly
                    }
                }).collect(Collectors.toList());
    }

    public String encryptProductPrice(BigDecimal val) throws Exception {
        if (val == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return EncryptionUtil.encrypt(val.toString());
    }

}