package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.PostDTO;
import com.juri.XNXGAMES.business.entity.Post;

import java.util.List;

public interface PostService {

	Post insertPost(long boardId, PostDTO.Request postRequest);
	void updatePost(long postId, PostDTO.Request postRequest);
	List<PostDTO.Response> getPostList(long boardId, BoardDTO.Criterial boardCriterial);
	PostDTO.Response getPost(long postId);
	void deletePost(long postId);
	int getAmountPost(long boardId);
	
}
