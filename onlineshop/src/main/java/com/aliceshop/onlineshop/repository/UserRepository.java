package com.aliceshop.onlineshop.repository;

import com.aliceshop.onlineshop.entity.UserVm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVm, String> {
    UserVm findByUsername(String username);
}
