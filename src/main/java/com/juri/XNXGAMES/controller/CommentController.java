package com.juri.XNXGAMES.controller;

import com.juri.XNXGAMES.DTO.CommentGetListDTO;
import com.juri.XNXGAMES.DTO.CommentPostDTO;
import com.juri.XNXGAMES.DTO.CommentPutDTO;
import com.juri.XNXGAMES.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/boards/{boardId}/posts/{postId}/comments")
	public ResponseEntity<Void> insertComment(@PathVariable Long postId, @RequestBody CommentPostDTO commentPostDTO) {
		commentService.insertComment(postId, commentPostDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> modifyComment(@PathVariable Long commentId, @RequestBody CommentPutDTO commentPutDTO) {
		commentService.modifyComment(commentId, commentPutDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}/comments")
	public ResponseEntity<List<CommentGetListDTO>> getCommentList(@PathVariable Long postId) {
		return new ResponseEntity<>(commentService.getCommentList(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
