package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.*;

import java.util.List;

public interface PostService {

	void insertPost(long boardId, PostPostDTO postPostDTO);
	void modifyPost(long postId, PostPutDTO postDTO);
	List<PostGetListDTO> getPostList(long boardId, BoardCriteriaDTO boardCriDTO);
	PostGetDTO getPost(long postId);
	void deletePost(long postId);
	int getAmountPost(long boardId);
	
}
