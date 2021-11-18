package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.DTO.CommentGetListDTO;
import com.juri.XNXGAMES.DTO.CommentPostDTO;
import com.juri.XNXGAMES.DTO.CommentPutDTO;

import java.util.List;

public interface CommentService {

	void insertComment(Long postId, CommentPostDTO commentPostDTO);
	void modifyComment(Long commentId, CommentPutDTO commentDTO);
	List<CommentGetListDTO> getCommentList(Long postId);
	void deleteComment(Long commentId);
	
}
