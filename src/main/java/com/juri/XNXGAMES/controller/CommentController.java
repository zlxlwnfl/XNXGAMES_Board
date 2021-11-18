package com.juri.XNXGAMES.controller;

import com.juri.XNXGAMES.DTO.CommentGetListDTO;
import com.juri.XNXGAMES.DTO.CommentPostDTO;
import com.juri.XNXGAMES.DTO.CommentPutDTO;
import com.juri.XNXGAMES.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/boards/{boardId}/posts/{postId}/comments")
	public void insertComment(@PathVariable Long postId, @RequestBody CommentPostDTO commentPostDTO) {
		commentService.insertComment(postId, commentPostDTO);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public void modifyComment(@PathVariable Long commentId, @RequestBody CommentPutDTO commentPutDTO) {
		commentService.modifyComment(commentId, commentPutDTO);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}/comments")
	public List<CommentGetListDTO> getCommentList(@PathVariable Long postId) {
		return commentService.getCommentList(postId);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		commentService.deleteComment(commentId);
	}
	
}
