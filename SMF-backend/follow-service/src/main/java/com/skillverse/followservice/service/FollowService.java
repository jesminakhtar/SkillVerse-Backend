package com.skillverse.followservice.service;

import java.util.List;

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
	public List<Follow> getFollowing(String followerId) {
		return followRepository.findByFollowerId(followerId);
	}
	
    /**
     * Returns list of users who are following the given user.
     * @param followingId -> ID of the user being followed
     * @return list of Follow objects
     */
	public List<Follow> getFollowers(String followingId) {
		return followRepository.findByFollowingId(followingId);
	}
	
}
