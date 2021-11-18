package com.juri.XNXGAMES.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.juri.XNXGAMES.DTO.CommentGetListDTO;
import com.juri.XNXGAMES.DTO.CommentPutDTO;
import com.juri.XNXGAMES.service.BoardService;
import com.juri.XNXGAMES.service.CommentService;
import com.juri.XNXGAMES.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/board/comment/*")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/")
	public void insertComment(@RequestBody CommentPutDTO commentDTO) {
		commentService.insertComment(commentDTO);
	}
	
	@PutMapping("/")
	public void modifyComment(@RequestBody CommentPutDTO commentDTO) {
		commentService.modifyComment(commentDTO);
	}
	
	@GetMapping("/")
	public List<CommentGetListDTO> getCommentList(Long postId) {
		return commentService.getCommentList(postId);
	}
	
	@DeleteMapping("/")
	public void deleteComment(Long commentId) {
		commentService.deleteComment(commentId);
	}
	
}
