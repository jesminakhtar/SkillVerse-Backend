package com.skillverse.authservice.dto;

import lombok.Data;

/**
 * DTO for verifying OTP.
 * Contains email and OTP entered by user. 
 */

@Data
public class OtpVerificationRequest {
	private String email;
	private String otp;
}
