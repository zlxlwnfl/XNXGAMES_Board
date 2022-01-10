package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentDTO;
import com.juri.XNXGAMES.business.entity.Comment;

import java.util.List;

public interface CommentService {

	Comment insertComment(long postId, CommentDTO.Request commentRequest);
	void updateComment(long commentId, CommentDTO.Request commentRequest);
	List<CommentDTO.Response> getCommentList(long postId);
	void deleteComment(long commentId);
	
}
