package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.*;

import java.util.List;

public interface PostService {

	void insertPost(Long boardId, PostPostDTO postPostDTO);
	void updatePost(Long postId, PostPutDTO postDTO);
	List<PostGetListDTO> getPostList(Long boardId, BoardCriteriaDTO boardCriDTO);
	PostGetDTO getPost(Long postId);
	void deletePost(Long postId);
	int getAmountPost(Long boardId);
	
}
