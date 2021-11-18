package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.DTO.*;
import com.juri.XNXGAMES.controller.EventDispatcher;
import com.juri.XNXGAMES.domain.BoardToMemberPostMessage;
import com.juri.XNXGAMES.domain.entity.PostEntity;
import com.juri.XNXGAMES.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	
	PostRepository postRepository;
	EventDispatcher eventDispatcher;

	@Override
	public void insertPost(@NonNull final Long boardId, @NonNull final PostPostDTO postPostDTO) {
		PostEntity post = PostEntity.builder()
				.type(postPostDTO.getPostType())
				.boardId(boardId)
				.writerId(postPostDTO.getWriterId())
				.title(postPostDTO.getTitle())
				.content(postPostDTO.getContent())
				.gameTagList(postPostDTO.getGameTagList())
				.build();

		PostEntity savedPost = postRepository.save(post);
		
		eventDispatcher.boardToMemberPostSend(
				new BoardToMemberPostMessage(
						"create", postPostDTO.getWriterId(), savedPost.getId()
						));
	}
	
	@Override
	public void updatePost(@NonNull final Long postId, @NonNull final PostPutDTO postPutDTO) {
		String title = postPutDTO.getTitle();
		String content = postPutDTO.getContent();
		
		postRepository.updateById(postId, title, content);
	}

	@Override
	public List<PostGetListDTO> getPostList(@NonNull final Long boardId, @NonNull final BoardCriteriaDTO boardCriDTO) {
		Pageable boardPaging = PageRequest.of(boardCriDTO.getCurrentPageNum() - 1,
												boardCriDTO.getAmountData());
		
		List<PostEntity> list = postRepository.findByBoardIdOrderByRegdateDesc(boardId, boardPaging);
		
		
		List<PostGetListDTO> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		
		for(PostEntity p : list) {
			PostGetListDTO dto = PostGetListDTO.builder()
					.postId(p.getId())
					.postType(p.getType())
					.writerId(p.getWriterId())
					.commentCount(p.getCommentCount())
					.regdate(format.format(p.getRegdate()))
					.title(p.getTitle())
					.hits(p.getHits())
					.heartCount(p.getHeartCount())
					.build();
			
			returnList.add(dto);
		}
		
		return returnList;
	}

	@Override
	public PostGetDTO getPost(@NonNull final Long postId) {
		PostEntity post = postRepository.findById(postId).get();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		PostGetDTO dto = PostGetDTO.builder()
				.postId(post.getId())
				.postType(post.getType())
				.writerId(post.getWriterId())
				.commentCount(post.getCommentCount())
				.regdate(format.format(post.getRegdate()))
				.title(post.getTitle())
				.content(post.getContent())
				.hits(post.getHits())
				.heartCount(post.getHeartCount())
				.gameTagList(post.getGameTagList())
				.build();
		
		return dto;
	}

	@Override
	public void deletePost(@NonNull final Long postId) {
		postRepository.deleteById(postId);
		
		eventDispatcher.boardToMemberPostSend(
				new BoardToMemberPostMessage(
						"delete", "", postId
						));
	}

	@Override
	public int getAmountPost(@NonNull final Long boardId) {
		return postRepository.findCountByBoardId(boardId);
	}

}
