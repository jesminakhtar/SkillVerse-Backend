package com.skillverse.postservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillverse.postservice.model.Post;

/**
 * Repository interface for Post CRUD operations.
 */

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	List<Post> findByUserId(String userId);
}
