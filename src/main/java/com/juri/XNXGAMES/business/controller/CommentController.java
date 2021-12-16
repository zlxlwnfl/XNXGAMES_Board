package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.CommentGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.CommentPostRequestDTO;
import com.juri.XNXGAMES.business.dto.CommentPostResponseDTO;
import com.juri.XNXGAMES.business.dto.CommentPutRequestDTO;
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
	public ResponseEntity<CommentPostResponseDTO> insertComment(@PathVariable long postId, @Valid @RequestBody CommentPostRequestDTO commentPostRequestDTO) {
		return new ResponseEntity<>(
				CommentPostResponseDTO.fromCommentEntity(commentService.insertComment(postId, commentPostRequestDTO)),
				HttpStatus.CREATED
		);
	}
	
	@PutMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> updateComment(@PathVariable long commentId, @Valid @RequestBody CommentPutRequestDTO commentPutRequestDTO) {
		commentService.updateComment(commentId, commentPutRequestDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/boards/{boardId}/posts/{postId}/comments/list")
	public ResponseEntity<List<CommentGetListResponseDTO>> getCommentList(@PathVariable long postId) {
		return new ResponseEntity<>(commentService.getCommentList(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{boardId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable long commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
