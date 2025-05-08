package com.example.grant_system.controllers;

import com.example.grant_system.entities.User;
import com.example.grant_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminSetupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create-admin")
    @ResponseBody
    public String createAdminUser() {
        String email = "admin@example.com";

        // Prevent duplicate admin
        if (userRepository.findByEmail(email).isPresent()) {
            return "Admin already exists.";
        }

        // Create new admin user
        User admin = new User();
        admin.setEmail(email);
        admin.setName("Administrator");
        admin.setPassword(passwordEncoder.encode("admin123")); // Use secure password!
        admin.setUserLevel("ADMIN");

        userRepository.save(admin);

        return "Admin account created. Use email: " + email + " and password: admin123";
    }
}
