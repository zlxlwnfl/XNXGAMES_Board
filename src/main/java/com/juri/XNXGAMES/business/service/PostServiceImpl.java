package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.PostDTO;
import com.juri.XNXGAMES.business.entity.Post;
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
	public Post insertPost(final long boardId, @NonNull final PostDTO.Request postRequest) {
		Post post = Post.builder()
				.type(postRequest.getPostType())
				.boardId(boardId)
				.writerId(postRequest.getWriterId())
				.title(postRequest.getTitle())
				.content(postRequest.getContent())
				.gameTagList(postRequest.getGameTagList())
				.build();

		Post savedPost;
		try {
			savedPost = postRepository.save(post);
		}
		catch(IllegalArgumentException e) {
			throw new PostException(ErrorCode.SERVER_CANNOT_SAVE);
		}

		try {
			eventDispatcher.boardToMemberPostSend(
					new BoardToMemberPostMessage(
							"create", postRequest.getWriterId(), savedPost.getPostId()
					));
		}
		catch(MaxRetriesExceededException e) {
			throw new PostException(ErrorCode.SERVER_CANNOT_SEND_MESSAGE);
		}

		return savedPost;
	}
	
	@Override
	public void updatePost(final long postId, @NonNull final PostDTO.Request postRequest) {
		String title = postRequest.getTitle();
		String content = postRequest.getContent();

		if(postRepository.existsByPostId(postId)) {
			postRepository.updateById(postId, title, content);
		}
		else {
			throw new PostException(ErrorCode.POST_NOT_EXIST);
		}
	}

	@Override
	public List<PostDTO.Response> getPostList(final long boardId, @NonNull final BoardDTO.Criterial boardCriterial) {
		Pageable boardPaging = PageRequest.of(boardCriterial.getCurrentPageNum() - 1,
				boardCriterial.getAmountData());
		
		List<Post> list = postRepository.findByBoardIdOrderByRegdateDesc(boardId, boardPaging);
		
		List<PostDTO.Response> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		
		for(Post p : list) {
			PostDTO.Response postResponse = PostDTO.Response.builder()
					.postId(p.getPostId())
					.type(p.getType())
					.writerId(p.getWriterId())
					.commentCount(p.getCommentCount())
					.regdate(format.format(p.getRegdate()))
					.title(p.getTitle())
					.hits(p.getHits())
					.heartCount(p.getHeartCount())
					.build();
			
			returnList.add(postResponse);
		}
		
		return returnList;
	}

	@Override
	public PostDTO.Response getPost(final long postId) {
		Optional<Post> postEntityOptional = postRepository.findById(postId);

		Post post;
		if(postEntityOptional.isPresent()) {
			post = postEntityOptional.get();
		}
		else {
			throw new PostException(ErrorCode.POST_NOT_EXIST);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		PostDTO.Response postResponse = PostDTO.Response.builder()
				.postId(post.getPostId())
				.type(post.getType())
				.writerId(post.getWriterId())
				.commentCount(post.getCommentCount())
				.regdate(format.format(post.getRegdate()))
				.title(post.getTitle())
				.content(post.getContent())
				.hits(post.getHits())
				.heartCount(post.getHeartCount())
				.gameTagList(post.getGameTagList())
				.build();
		
		return postResponse;
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
