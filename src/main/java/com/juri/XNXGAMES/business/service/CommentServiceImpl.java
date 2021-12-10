package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.CommentPostRequestDTO;
import com.juri.XNXGAMES.business.dto.CommentPutRequestDTO;
import com.juri.XNXGAMES.business.entity.CommentEntity;
import com.juri.XNXGAMES.business.exception.CommentException;
import com.juri.XNXGAMES.business.exception.ErrorCode;
import com.juri.XNXGAMES.business.repository.CommentRepository;
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
	public CommentEntity insertComment(final long postId, @NonNull final CommentPostRequestDTO commentPostRequestDTO) {
		CommentEntity comment = CommentEntity.builder()
				.postId(postId)
				.writerId(commentPostRequestDTO.getWriterId())
				.content(commentPostRequestDTO.getContent())
				.build();

		try {
			return commentRepository.save(comment);
		}
		catch(IllegalArgumentException e) {
			throw new CommentException(ErrorCode.SERVER_CANNOT_SAVE);
		}
	}
	
	@Override
	public void updateComment(final long commentId, @NonNull final CommentPutRequestDTO commentPutRequestDTO) {
		String content = commentPutRequestDTO.getContent();

		if(commentRepository.existsByCommentId(commentId)) {
			commentRepository.updateById(commentId, content);
		}
		else {
			throw new CommentException(ErrorCode.COMMENT_NOT_EXIST);
		}
	}

	@Override
	public List<CommentGetListResponseDTO> getCommentList(final long postId) {
		List<CommentEntity> list = commentRepository.findByPostIdOrderByRegdateDesc(postId);
		
		List<CommentGetListResponseDTO> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(CommentEntity comment : list) {
			CommentGetListResponseDTO dto = CommentGetListResponseDTO.builder()
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
