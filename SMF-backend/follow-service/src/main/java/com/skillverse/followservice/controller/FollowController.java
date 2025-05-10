package com.skillverse.followservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillverse.followservice.model.Follow;
import com.skillverse.followservice.service.FollowService;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping("/follow")
public class FollowController {

	@Autowired
	private FollowService followService;
	
	/**
	 * API to follow a user.
	 * @param follow -> Follow object containing followerId and followingId
     * @return saved Follow object
	 */
	@PostMapping
	public Follow followUser(@Valid @RequestBody Follow follow) {
		return followService.followUser(follow);
	}
	
	/**
	 * API to unfollow a user.
	 * @param followerId -> ID of the follower
	 * @param followingId -> ID of the user being unfollowed
	 */
	@DeleteMapping
	public void unfollowUser(@RequestParam String followerId, @RequestParam String followingId) {
		followService.unfollowUser(followerId, followingId);
	}
	
	/**
	 * API to get list of users this user is following.
     * @param followerId -> ID of the follower
     * @return list of Follow objects
	 */
	@GetMapping("/following/{followerId}")
	public List<Follow> getFollowing(@PathVariable String followerId) {
		return followService.getFollowing(followerId);
	}
	
	/**
	 * API to get list of followers of a user.
     * @param followingId -> ID of the user being followed
     * @return list of Follow objects
	 */
	@GetMapping("/followers/{followingId}")
	public List<Follow> getFollowers(@PathVariable String followingId) {
		return followService.getFollowers(followingId);
	}
}
