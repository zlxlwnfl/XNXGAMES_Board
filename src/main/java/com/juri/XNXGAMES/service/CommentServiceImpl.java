package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.DTO.CommentGetListDTO;
import com.juri.XNXGAMES.DTO.CommentPostDTO;
import com.juri.XNXGAMES.DTO.CommentPutDTO;
import com.juri.XNXGAMES.domain.entity.CommentEntity;
import com.juri.XNXGAMES.domain.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	CommentRepository commentRepository;

	@Override
	public void insertComment(@NonNull final Long postId, @NonNull final CommentPostDTO commentPostDTO) {
		CommentEntity comment = CommentEntity.builder()
				.postId(postId)
				.writerId(commentPostDTO.getWriterId())
				.content(commentPostDTO.getContent())
				.build();
		
		commentRepository.save(comment);
	}
	
	@Override
	public void modifyComment(@NonNull final Long commentId, @NonNull final CommentPutDTO commentPutDTO) {
		String content = commentPutDTO.getContent();
		
		commentRepository.updateById(commentId, content);
	}

	@Override
	public List<CommentGetListDTO> getCommentList(@NonNull final Long postId) {
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
	public void deleteComment(@NonNull final Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
