package com.spotify.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







import com.spotify.dto.UserDTO;
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
    
    
    // 🗑️ DELETE user by userName


    @DeleteMapping("/users/username/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        if (userRepo.existsByUsername(username)) {
            userRepo.deleteByUsername(username);
            return ResponseEntity.ok("✅ User deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ User not found!");
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
    
    
 // ✅ Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            User user = userRepo.findById(id).get();

            UserDTO dto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
            );

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ User not found!");
        }
    }


    
    // Get User by UserName
    @GetMapping("/username/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        Optional<User> userOpt = userRepo.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO dto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ User not found!");
        }
    }



    
}
