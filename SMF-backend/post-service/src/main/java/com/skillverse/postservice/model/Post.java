package com.skillverse.postservice.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Post Model represents a user-generated post in SkillVerse.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	private String id;
	private String userId;      // ID of the user who craeted the post
	private String content;     // text content of the post
	private String imageUrl;    // optional image URL
	private Date createdAt;     // post creation time
	private List<String> likes; // list of userIds who liked
	private List<String> comments;  // optional : can be expanded later
}
