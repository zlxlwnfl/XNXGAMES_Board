package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.CommentDTO;
import com.juri.XNXGAMES.business.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/boards/{boardId}/posts/{postId}/comments")
	public ResponseEntity<CommentDTO.Response> insertComment(@PathVariable long postId, @Valid @RequestBody CommentDTO.Request commentRequest) {
		return new ResponseEntity<>(
				CommentDTO.Response.fromComment(commentService.insertComment(postId, commentRequest)),
				HttpStatus.CREATED
		);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> updateComment(@PathVariable long commentId, @Valid @RequestBody CommentDTO.Request commentRequest) {
		commentService.updateComment(commentId, commentRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}/comments/list")
	public ResponseEntity<List<CommentDTO.Response>> getCommentList(@PathVariable long postId) {
		return new ResponseEntity<>(commentService.getCommentList(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable long commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
