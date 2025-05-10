package com.skillverse.authservice.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * Service to temporarily store OTPs in memory.
 * Maps : email -> (OTP + expiry time).
 */

@Service
public class OtpStorageService {
	private final Map<String, OtpEntry> otpStore = new ConcurrentHashMap<>();
	
	
	/**
	 * Store OTP for an email with expiry time.
	 */
	public void storeOtp(String email, String otp, LocalDateTime expiresAt) {
		otpStore.put(email, new OtpEntry(otp, expiresAt));
	}
	
	
	
	/**
	 * Validate OTP for an email.
	 * REturns true if OTP matches and not expired.
	 */
	public boolean validateOtp(String email, String otp) {
		OtpEntry entry = otpStore.get(email); // Returns the OtpEntry object mapped with the email.
		if(entry == null) return false;
		if(entry.expiresAt.isBefore(LocalDateTime.now())) return false;
		return entry.otp.equals(otp);
	}
	
	
	/**
	 * Removes OTP after verification or expiry.
	 */
	public void removeOtp(String email) {
		otpStore.remove(email); // Removes the OtpEntry for this email.
	}
	
	
	/**
	 * Internal class to store OTP + expiry.
	 */
	public static class OtpEntry {
		String otp;
		LocalDateTime expiresAt; 
		
		public OtpEntry(String otp, LocalDateTime expiresAt) {
			this.otp = otp;
			this.expiresAt = expiresAt;
		}
	}
}
