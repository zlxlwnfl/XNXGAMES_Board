package com.juri.XNXGAMES.controller;

import com.juri.XNXGAMES.DTO.*;
import com.juri.XNXGAMES.service.BoardService;
import com.juri.XNXGAMES.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final BoardService boardService;
	private final PostService postService;

	@GetMapping("/boards")
	public Long getBoardId(@RequestParam String boardType,
							@RequestParam String boardSubType) {
		return boardService.searchBoard(boardType, boardSubType);
	}
	
	@PostMapping("/boards/{boardId}/posts")
	public void insertPost(@PathVariable Long boardId, @RequestBody PostPostDTO postPostDTO) {
		postService.insertPost(boardId, postPostDTO);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}")
	public void updatePost(@PathVariable Long postId, @RequestBody PostPutDTO postPutDTO) {
		postService.updatePost(postId, postPutDTO);
	}
	
	@GetMapping("/boards/{boardId}/posts")
	public List<PostGetListDTO> getPostList(@PathVariable Long boardId, @RequestBody BoardCriteriaDTO boardCriDTO) {
		return postService.getPostList(boardId, boardCriDTO);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}")
	public PostGetDTO getPost(@PathVariable Long postId) {
		return postService.getPost(postId);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}")
	public void deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
	}
	
	@GetMapping("/boards/{boardId}/posts/amount")
	public int getAmountPost(@PathVariable Long boardId) {
		return postService.getAmountPost(boardId);
	}
	
}
