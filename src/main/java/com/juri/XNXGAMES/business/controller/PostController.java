package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.*;
import com.juri.XNXGAMES.business.entity.PostEntity;
import com.juri.XNXGAMES.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PostMapping("/boards/{boardId}/posts")
	public ResponseEntity<PostEntity> insertPost(@PathVariable long boardId, @RequestBody PostPostDTO postPostDTO) {
		return new ResponseEntity<>(postService.insertPost(boardId, postPostDTO), HttpStatus.CREATED);
	}

	@PutMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<Void> updatePost(@PathVariable long postId, @RequestBody PostPutDTO postPutDTO) {
		postService.modifyPost(postId, postPutDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/list")
	public ResponseEntity<List<PostGetListDTO>> getPostList(
			@PathVariable long boardId,
			@RequestParam int currentPageNum,
			@RequestParam int amountData) {
		return new ResponseEntity<>(postService.getPostList(boardId, new BoardCriteriaDTO(currentPageNum, amountData)), HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<PostGetDTO> getPost(@PathVariable long postId) {
		return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable long postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/amount")
	public ResponseEntity<Integer> getAmountPost(@PathVariable long boardId) {
		return new ResponseEntity<>(postService.getAmountPost(boardId), HttpStatus.OK);
	}
	
}
