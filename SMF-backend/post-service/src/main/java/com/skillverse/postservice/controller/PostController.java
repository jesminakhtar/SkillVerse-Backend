package com.skillverse.postservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillverse.postservice.model.Post;
import com.skillverse.postservice.repository.PostRepository;

/**
 * REST Controller for mapping posts.
 */

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	/**
	 * Create a new post.
	 */
	@PostMapping
	public Post createPost(@RequestBody Post post) {
		post.setCreatedAt(new Date());
		return postRepository.save(post);
	}
	
	/**
	 * Get a post by ID.
	 */
	@GetMapping("/{id}")
	public Optional<Post> getPostById(@PathVariable String id) {
		return postRepository.findById(id);
	}
	
	/**
	 * Get all posts by a user.
	 */
	@GetMapping("/user/{userId}")
	public List<Post> getPostByUserId(@PathVariable String userId) {
		return postRepository.findByUserId(userId);
	}
	
	/**
	 * Get all posts by a user.
	 */
	@PostMapping("/from-users")
	public List<Post> getPostFromUsers(@RequestBody List<String> userIds) {
		return postRepository.findByUserIdIn(userIds);
	}
	
	/**
	 * Update a post by ID.
	 */
	@PutMapping("/{id}")
	public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
		Optional<Post> existingPost = postRepository.findById(id);
		if(existingPost.isPresent()) {
			Post post = existingPost.get();
			if(updatedPost.getContent() != null) {
				post.setContent(updatedPost.getContent());
			}
			if(updatedPost.getImageUrl() != null) {
				post.setImageUrl(updatedPost.getImageUrl());
			}
			return postRepository.save(post);
		}
		return null;
	}
	
	/**
	 * Delete a post by ID.
	 */
	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable String id) {
		postRepository.deleteById(id);
		return "Post deleted";
	}
}
