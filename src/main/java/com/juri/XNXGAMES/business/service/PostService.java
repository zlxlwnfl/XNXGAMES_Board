package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.*;
import com.juri.XNXGAMES.business.entity.PostEntity;

import java.util.List;

public interface PostService {

	PostEntity insertPost(long boardId, PostPostRequestDTO postPostRequestDTO);
	void modifyPost(long postId, PostPutRequestDTO postDTO);
	List<PostGetListResponseDTO> getPostList(long boardId, BoardCriteriaDTO boardCriDTO);
	PostGetResponseDTO getPost(long postId);
	void deletePost(long postId);
	int getAmountPost(long boardId);
	
}
