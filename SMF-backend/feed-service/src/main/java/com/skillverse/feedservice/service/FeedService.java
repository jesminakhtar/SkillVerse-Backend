package com.skillverse.feedservice.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.skillverse.feedservice.dto.FeedItem;
import com.skillverse.feedservice.dto.FeedResponse;

// Business logic to build the user'd feed
@Service
public class FeedService {
	@Autowired
	private RestTemplate restTemplate;
	
	private final String FOLLOW_SERVICE_URL = "http://localhost:8083/follow/following/";
	private final String POST_SERVICE_URL = "http://localhost:8082/posts/from-users";
	
	public FeedResponse getFeedResponse(String userId) {
		// Step 1: Get list of userIds the current user is following
		List<String> followingUserIds = Arrays.asList(
				restTemplate.getForObject(FOLLOW_SERVICE_URL + userId, String[].class)
		);
		
		System.out.println("followingUserIds : " + followingUserIds.toString());
		
		if (followingUserIds.isEmpty()) {
			return new FeedResponse(Collections.emptyList());
		}
		
		
		// Step 2: Send userIds to PostService to get their posts
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<List<String>> request = new HttpEntity<>(followingUserIds, headers);
		
		ResponseEntity<FeedItem[]> response = restTemplate.postForEntity(
				POST_SERVICE_URL, 
				request, 
				FeedItem[].class
		);
		
		FeedItem[] posts = response.getBody();
		
		// Step 3: Sort posts by timestamp descending
		
		List<FeedItem> sortedPosts = Arrays.stream(posts)
				.sorted(Comparator.comparing(FeedItem::getCreatedAt).reversed())
				.collect(Collectors.toList());
		
		return new FeedResponse(sortedPosts);
				
	}
}
