package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentDTO;
import com.juri.XNXGAMES.business.entity.Comment;
import com.juri.XNXGAMES.business.exception.CommentException;
import com.juri.XNXGAMES.business.repository.CommentRepository;
import com.juri.XNXGAMES.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;

	@Override
	public Comment insertComment(final long postId, @NonNull final CommentDTO.Request commentRequest) {
		Comment comment = Comment.builder()
				.postId(postId)
				.writerId(commentRequest.getWriterId())
				.content(commentRequest.getContent())
				.build();

		try {
			return commentRepository.save(comment);
		}
		catch(IllegalArgumentException e) {
			throw new CommentException(ErrorCode.SERVER_CANNOT_SAVE);
		}
	}
	
	@Override
	public void updateComment(final long commentId, @NonNull final CommentDTO.Request commentRequest) {
		String content = commentRequest.getContent();

		if(commentRepository.existsByCommentId(commentId)) {
			commentRepository.updateById(commentId, content);
		}
		else {
			throw new CommentException(ErrorCode.COMMENT_NOT_EXIST);
		}
	}

	@Override
	public List<CommentDTO.Response> getCommentList(final long postId) {
		List<Comment> list = commentRepository.findByPostIdOrderByRegdateDesc(postId);
		
		List<CommentDTO.Response> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(Comment comment : list) {
			CommentDTO.Response dto = CommentDTO.Response.builder()
					.commentId(comment.getCommentId())
					.writerId(comment.getWriterId())
					.regdate(format.format(comment.getRegdate()))
					.content(comment.getContent())
					.heartCount(comment.getHeartCount())
					.build();
			
			returnList.add(dto);
		}
		
		return returnList;
	}

	@Override
	public void deleteComment(final long commentId) {
		if(commentRepository.existsByCommentId(commentId)) {
			commentRepository.deleteById(commentId);
		}
		else {
			throw new CommentException(ErrorCode.COMMENT_NOT_EXIST);
		}
	}

}
