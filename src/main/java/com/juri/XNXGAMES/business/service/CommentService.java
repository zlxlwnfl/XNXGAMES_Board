package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.CommentPostRequestDTO;
import com.juri.XNXGAMES.business.dto.CommentPutRequestDTO;
import com.juri.XNXGAMES.business.entity.CommentEntity;

import java.util.List;

public interface CommentService {

	CommentEntity insertComment(long postId, CommentPostRequestDTO commentPostRequestDTO);
	void updateComment(long commentId, CommentPutRequestDTO commentDTO);
	List<CommentGetListResponseDTO> getCommentList(long postId);
	void deleteComment(long commentId);
	
}
