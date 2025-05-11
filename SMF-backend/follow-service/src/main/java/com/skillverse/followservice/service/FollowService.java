package com.skillverse.followservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillverse.followservice.model.Follow;
import com.skillverse.followservice.repository.FollowRepository;

/**
 * FollowService contains business logic related to following and unfollowing users.
 */
@Service
public class FollowService {
	@Autowired
	private FollowRepository followRepository;
	
    /**
     * Creates a follow relationship between follower and following.
     * @param follow -> Follow object containing followerId and followingId
     * @return saved Follow object
     */
	public Follow followUser(Follow follow) {
		return followRepository.save(follow);
	}
	
	/**
	 * Removes a follow relationship.
	 * @param followerId -> ID of the follower
	 * @param followingId -> ID of the user being unfollowed
	 */
	public void unfollowUser(String followerId, String followingId) {
		followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);
	}
	
	
    /**
     * Returns list of users that the given user is following.
     * @param followerId -> ID of the follower
     * @return list of Follow objects
     */
	public List<String> getFollowing(String followerId) {
		List<Follow> follows =  followRepository.findByFollowerId(followerId);
		return follows.stream()
					.map(Follow::getFollowingId)
					.collect(Collectors.toList());
	}
	
    /**
     * Returns list of users who are following the given user.
     * @param followingId -> ID of the user being followed
     * @return list of Follow objects
     */
	public List<String> getFollowers(String followingId) {
		List<Follow> follows =  followRepository.findByFollowingId(followingId);
		return follows.stream()
					.map(Follow::getFollowerId)
					.collect(Collectors.toList());
	}
	
}
