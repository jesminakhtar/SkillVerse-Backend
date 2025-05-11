package com.skillverse.followservice.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a follow relationship between two users.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
	@Id
	private String id;
	
	@NotBlank(message="Follower ID must not be blank")
	private String followerId;   //who follows 
	
	@NotBlank(message="Following ID must not be blank")
	private String followingId;	 //who is being followed
}
