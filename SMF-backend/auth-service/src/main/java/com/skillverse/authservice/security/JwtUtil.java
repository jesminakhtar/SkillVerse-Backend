package com.skillverse.authservice.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Utility class to generate JWT tokens.
 */
@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	/**
	 * Generates JWT token for given email.
	 * Token expires after 1 day.
	 */
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
}
