package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentGetListDTO;
import com.juri.XNXGAMES.business.dto.CommentPostDTO;
import com.juri.XNXGAMES.business.dto.CommentPutDTO;

import java.util.List;

public interface CommentService {

	void insertComment(Long postId, CommentPostDTO commentPostDTO);
	void modifyComment(Long commentId, CommentPutDTO commentDTO);
	List<CommentGetListDTO> getCommentList(Long postId);
	void deleteComment(Long commentId);
	
}
