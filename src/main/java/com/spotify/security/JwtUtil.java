	package com.spotify.security;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	  private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256-bit strong key

	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .signWith(SECRET_KEY)
	                .compact();
	    }
	 
	    public Claims getClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
	    
	    public String extractUsername(String token) {
	        return getClaims(token).getSubject();
	    }

	    public boolean validateToken(String token, UserDetails userDetails) {
	        String username = extractUsername(token);
	        return username.equals(userDetails.getUsername());
	    }

}

