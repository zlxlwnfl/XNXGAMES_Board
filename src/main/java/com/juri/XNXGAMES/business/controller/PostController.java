package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.*;
import com.juri.XNXGAMES.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PostMapping("/boards/{boardId}/posts")
	public ResponseEntity<PostPostResponseDTO> insertPost(@PathVariable long boardId, @Valid @RequestBody PostPostRequestDTO postPostRequestDTO) {
		return new ResponseEntity<>(
				PostPostResponseDTO.fromPostEntity(postService.insertPost(boardId, postPostRequestDTO)),
				HttpStatus.CREATED
		);
	}

	@PutMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<Void> updatePost(@PathVariable long postId, @Valid @RequestBody PostPutRequestDTO postPutRequestDTO) {
		postService.updatePost(postId, postPutRequestDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/list")
	public ResponseEntity<List<PostGetListResponseDTO>> getPostList(
			@PathVariable long boardId,
			@RequestParam int currentPageNum,
			@RequestParam int amountData) {
		return new ResponseEntity<>(postService.getPostList(boardId, new BoardCriteriaDTO(currentPageNum, amountData)), HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<PostGetResponseDTO> getPost(@PathVariable long postId) {
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
