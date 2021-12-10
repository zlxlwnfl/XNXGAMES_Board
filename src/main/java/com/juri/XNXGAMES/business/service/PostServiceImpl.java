package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.*;
import com.juri.XNXGAMES.business.entity.PostEntity;
import com.juri.XNXGAMES.business.exception.ErrorCode;
import com.juri.XNXGAMES.business.exception.PostException;
import com.juri.XNXGAMES.business.message.BoardToMemberPostMessage;
import com.juri.XNXGAMES.business.repository.PostRepository;
import io.github.resilience4j.retry.MaxRetriesExceededException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final EventDispatcher eventDispatcher;

	@Override
	public PostEntity insertPost(final long boardId, @NonNull final PostPostDTO postPostDTO) {
		PostEntity post = PostEntity.builder()
				.type(postPostDTO.getPostType())
				.boardId(boardId)
				.writerId(postPostDTO.getWriterId())
				.title(postPostDTO.getTitle())
				.content(postPostDTO.getContent())
				.gameTagList(postPostDTO.getGameTagList())
				.build();

		PostEntity savedPost;
		try {
			savedPost = postRepository.save(post);
		}
		catch(IllegalArgumentException e) {
			throw new PostException(ErrorCode.SERVER_CANNOT_SAVE);
		}

		try {
			eventDispatcher.boardToMemberPostSend(
					new BoardToMemberPostMessage(
							"create", postPostDTO.getWriterId(), savedPost.getPostId()
					));
		}
		catch(MaxRetriesExceededException e) {
			throw new PostException(ErrorCode.SERVER_CANNOT_SEND_MESSAGE);
		}

		return savedPost;
	}
	
	@Override
	public void modifyPost(final long postId, @NonNull final PostPutDTO postPutDTO) {
		String title = postPutDTO.getTitle();
		String content = postPutDTO.getContent();

		if(postRepository.existsByPostId(postId)) {
			postRepository.updateById(postId, title, content);
		}
		else {
			throw new PostException(ErrorCode.POST_NOT_EXIST);
		}
	}

	@Override
	public List<PostGetListDTO> getPostList(final long boardId, @NonNull final BoardCriteriaDTO boardCriDTO) {
		Pageable boardPaging = PageRequest.of(boardCriDTO.getCurrentPageNum() - 1,
												boardCriDTO.getAmountData());
		
		List<PostEntity> list = postRepository.findByBoardIdOrderByRegdateDesc(boardId, boardPaging);
		
		List<PostGetListDTO> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		
		for(PostEntity p : list) {
			PostGetListDTO dto = PostGetListDTO.builder()
					.postId(p.getPostId())
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
	public PostGetDTO getPost(final long postId) {
		Optional<PostEntity> postEntityOptional = postRepository.findById(postId);

		PostEntity postEntity;
		if(postEntityOptional.isPresent()) {
			postEntity = postEntityOptional.get();
		}
		else {
			throw new PostException(ErrorCode.POST_NOT_EXIST);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		PostGetDTO postGetDTO = PostGetDTO.builder()
				.postId(postEntity.getPostId())
				.postType(postEntity.getType())
				.writerId(postEntity.getWriterId())
				.commentCount(postEntity.getCommentCount())
				.regdate(format.format(postEntity.getRegdate()))
				.title(postEntity.getTitle())
				.content(postEntity.getContent())
				.hits(postEntity.getHits())
				.heartCount(postEntity.getHeartCount())
				.gameTagList(postEntity.getGameTagList())
				.build();
		
		return postGetDTO;
	}

	@Override
	public void deletePost(final long postId) {
		if(postRepository.existsByPostId(postId)) {
			postRepository.deleteById(postId);
		}
		else {
			throw new PostException(ErrorCode.POST_NOT_EXIST);
		}

		try {
			eventDispatcher.boardToMemberPostSend(
					new BoardToMemberPostMessage(
							"delete", "", postId
					));
		}
		catch(MaxRetriesExceededException e) {
			throw new PostException(ErrorCode.SERVER_CANNOT_SEND_MESSAGE);
		}
	}

	@Override
	public int getAmountPost(final long boardId) {
		return postRepository.findCountByBoardId(boardId);
	}

}
