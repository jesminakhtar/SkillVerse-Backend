package com.skillverse.followservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillverse.followservice.model.Follow;

@Repository
public interface FollowRepository extends MongoRepository<Follow, String>{
	List<Follow> findByFollowerId(String followerId);
	List<Follow> findByFollowingId(String followingId);
	void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
}
