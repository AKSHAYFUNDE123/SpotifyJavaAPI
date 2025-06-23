package com.spotify.controller;

import com.spotify.dto.LoginRequest;
import com.spotify.model.User;
import com.spotify.repository.UserRepository;
import com.spotify.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println("👉 Register API called with: " + user);

        if (userRepo.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        System.out.println("✅ User saved in DB!");
        return "User registered successfully!";
    }

    


    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepo.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user.getUsername());
            } else {
                return "Invalid credentials!";
            }
        } else {
            return "User not found!";
        }
    }
}
