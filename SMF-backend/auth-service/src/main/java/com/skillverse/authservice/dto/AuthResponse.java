package com.skillverse.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for authentication response.
 * Returns JWT token and optional message.
 */

@Data
@AllArgsConstructor
public class AuthResponse {
	private String token;
	private String message;
}
