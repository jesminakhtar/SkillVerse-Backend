package com.skillverse.feedservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillverse.feedservice.dto.FeedResponse;
import com.skillverse.feedservice.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	// Returns the list of posts for the user's feed
	@GetMapping("/{userId}") 
	public FeedResponse getFeed(@PathVariable String userId) {
		return feedService.getFeedResponse(userId);		
	}
}
