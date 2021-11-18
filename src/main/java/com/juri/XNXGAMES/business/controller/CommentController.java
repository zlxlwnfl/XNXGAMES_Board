package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.CommentGetListDTO;
import com.juri.XNXGAMES.business.dto.CommentPostDTO;
import com.juri.XNXGAMES.business.dto.CommentPutDTO;
import com.juri.XNXGAMES.business.service.CommentService;
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
	public ResponseEntity<Void> insertComment(@PathVariable long postId, @RequestBody CommentPostDTO commentPostDTO) {
		commentService.insertComment(postId, commentPostDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> modifyComment(@PathVariable long commentId, @RequestBody CommentPutDTO commentPutDTO) {
		commentService.modifyComment(commentId, commentPutDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}/comments")
	public ResponseEntity<List<CommentGetListDTO>> getCommentList(@PathVariable long postId) {
		return new ResponseEntity<>(commentService.getCommentList(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable long commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
