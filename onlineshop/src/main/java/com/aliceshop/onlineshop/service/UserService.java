package com.aliceshop.onlineshop.service;

import com.aliceshop.onlineshop.entity.UserVm;
import com.aliceshop.onlineshop.repository.UserRepository;
import com.aliceshop.onlineshop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserVm findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(UserVm user) throws NoSuchAlgorithmException {
        user.setPassword(SecurityUtil.hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    // ✅ Add this for JSF login
    public boolean checkLogin(String username, String rawPassword) {
        UserVm user = findByUsername(username);
        if (user == null) return false;

        try {
            String hashed = SecurityUtil.hashPassword(rawPassword);
            return hashed.equals(user.getPassword());
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }
}
