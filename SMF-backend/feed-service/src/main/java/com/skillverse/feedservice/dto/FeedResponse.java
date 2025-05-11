package com.skillverse.feedservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

// Final feed response sent to the client
@Data
@AllArgsConstructor
public class FeedResponse {
	private List<FeedItem> posts;
} 
