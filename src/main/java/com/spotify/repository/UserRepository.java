package com.spotify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.spotify.model.User;

import jakarta.transaction.Transactional;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
   
   
    
    @Modifying
    @Transactional
    void deleteByUsername(String username);
}
