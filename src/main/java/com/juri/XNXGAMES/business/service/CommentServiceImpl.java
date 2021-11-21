package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.CommentGetListDTO;
import com.juri.XNXGAMES.business.dto.CommentPostDTO;
import com.juri.XNXGAMES.business.dto.CommentPutDTO;
import com.juri.XNXGAMES.business.entity.CommentEntity;
import com.juri.XNXGAMES.business.exception.CommentException;
import com.juri.XNXGAMES.business.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
	public CommentEntity insertComment(final long postId, @NonNull final CommentPostDTO commentPostDTO) {
		CommentEntity comment = CommentEntity.builder()
				.postId(postId)
				.writerId(commentPostDTO.getWriterId())
				.content(commentPostDTO.getContent())
				.build();

		try {
			return commentRepository.save(comment);
		}
		catch(IllegalArgumentException e) {
			throw new CommentException(HttpStatus.INTERNAL_SERVER_ERROR, "server can't save");
		}
	}
	
	@Override
	public void modifyComment(final long commentId, @NonNull final CommentPutDTO commentPutDTO) {
		String content = commentPutDTO.getContent();

		if(commentRepository.existsByCommentId(commentId)) {
			commentRepository.updateById(commentId, content);
		}
		else {
			throw new CommentException(HttpStatus.BAD_REQUEST, "commentId not exist");
		}
	}

	@Override
	public List<CommentGetListDTO> getCommentList(final long postId) {
		List<CommentEntity> list = commentRepository.findByPostIdOrderByRegdateDesc(postId);
		
		List<CommentGetListDTO> returnList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(CommentEntity comment : list) {
			CommentGetListDTO dto = CommentGetListDTO.builder()
					.commentId(comment.getId())
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
		commentRepository.deleteById(commentId);
	}

}
