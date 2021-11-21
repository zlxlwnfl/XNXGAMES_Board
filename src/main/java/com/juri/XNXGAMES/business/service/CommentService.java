package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentGetListDTO;
import com.juri.XNXGAMES.business.dto.CommentPostDTO;
import com.juri.XNXGAMES.business.dto.CommentPutDTO;
import com.juri.XNXGAMES.business.entity.CommentEntity;

import java.util.List;

public interface CommentService {

	CommentEntity insertComment(long postId, CommentPostDTO commentPostDTO);
	void modifyComment(long commentId, CommentPutDTO commentDTO);
	List<CommentGetListDTO> getCommentList(long postId);
	void deleteComment(long commentId);
	
}
