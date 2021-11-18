package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.*;
import com.juri.XNXGAMES.business.service.BoardService;
import com.juri.XNXGAMES.business.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final BoardService boardService;
	private final PostService postService;

	@GetMapping("/boards")
	public ResponseEntity<Long> getBoardId(@RequestParam String boardType, @RequestParam String boardSubType) {
		return new ResponseEntity<>(boardService.getBoardId(boardType, boardSubType), HttpStatus.OK);
	}
	
	@PostMapping("/boards/{boardId}/posts")
	public ResponseEntity<Void> insertPost(@PathVariable Long boardId, @RequestBody PostPostDTO postPostDTO) {
		postService.insertPost(boardId, postPostDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostPutDTO postPutDTO) {
		postService.updatePost(postId, postPutDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts")
	public ResponseEntity<List<PostGetListDTO>> getPostList(@PathVariable Long boardId, @RequestBody BoardCriteriaDTO boardCriDTO) {
		return new ResponseEntity<>(postService.getPostList(boardId, boardCriDTO), HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<PostGetDTO> getPost(@PathVariable Long postId) {
		return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/amount")
	public ResponseEntity<Integer> getAmountPost(@PathVariable Long boardId) {
		return new ResponseEntity<>(postService.getAmountPost(boardId), HttpStatus.OK);
	}
	
}
