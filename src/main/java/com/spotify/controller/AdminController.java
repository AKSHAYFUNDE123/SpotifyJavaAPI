package com.spotify.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.model.User;
import com.spotify.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    // ✅ GET: View all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    
 // 🗑️ DELETE user by ID
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
        	userRepo.deleteById(id);
            return "✅ User deleted successfully!";
        } else {
            return "❌ User not found!";
        } 
    }
    
    
    // Update role from Admin 
    
    @PutMapping("/users/{id}/role")
    public String updateUserRole(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newRole = request.get("role");

        return userRepo.findById(id).map(user -> {
            user.setRole(newRole);
            userRepo.save(user);
            return "✅ User role updated to: " + newRole;
        }).orElse("❌ User not found");
    }
    

    
    
}
