package com.skillverse.authservice.dto;

import lombok.Data;

/** 
 * DTO for requesting an OTP.
 * COntains user's email.
 */

@Data
public class OtpRequest {
	private String email;
}
