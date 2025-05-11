package com.skillverse.authservice.controller;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillverse.authservice.dto.AuthResponse;
import com.skillverse.authservice.dto.OtpRequest;
import com.skillverse.authservice.dto.OtpVerificationRequest;
import com.skillverse.authservice.security.JwtUtil;
import com.skillverse.authservice.service.EmailService;
import com.skillverse.authservice.service.OtpStorageService;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller for authentication endpoints.
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final OtpStorageService otpStorageService;
	private final EmailService emailService;
	private final JwtUtil jwtUtil;
	
	/**
	 * POST /auth/request-otp
	 * Generates OTP -> stores -> sends email.
	 */
	@PostMapping("/request-otp")
	public ResponseEntity<?> requestOtp(@RequestBody OtpRequest otpRequest) {
		String email = otpRequest.getEmail();
		String otp = String.format("%06d", new Random().nextInt(999999)); //Random 6-digit OTP
		otpStorageService.storeOtp(email, otp, LocalDateTime.now().plusMinutes(5));
		emailService.sendOtpEmail(email, otp);
		return ResponseEntity.ok("OTP sent to email.");
	}
	
	/**
	 * POST /auth/verify-otp
	 * Verifies OTP -> returns JWT if valid.
	 */
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationRequest request) {
		boolean valid = otpStorageService.validateOtp(request.getEmail(), request.getOtp());
		if(valid) {
			String token = jwtUtil.generateToken(request.getEmail());
			return ResponseEntity.ok(new AuthResponse(token, "Authentication successful"));
		} else {
			return ResponseEntity.status(401).body(new AuthResponse(null, "Invalid or expired OTP"));
		}
		
	}
}
