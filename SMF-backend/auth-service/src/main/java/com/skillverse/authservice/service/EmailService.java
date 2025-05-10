package com.skillverse.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service to send OTP emails.
 */

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * Sends OTP email to user.
	 */
	public void sendOtpEmail(String toEmail, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("SkillVerse OTP Code");
		message.setText("Your OTP is" + otp + "\nValid for 5 minutes.");
		mailSender.send(message);
	}
	
}
