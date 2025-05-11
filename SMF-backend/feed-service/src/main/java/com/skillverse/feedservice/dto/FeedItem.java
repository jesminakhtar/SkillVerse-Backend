package com.skillverse.feedservice.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

// Represent a single post in the feed
@Data
public class FeedItem {
	private String id;
	private String userId;
	private String content;
	private String imageUrl;
	private Date createdAt;     
	private List<String> likes; 
	private List<String> comments; 
}
