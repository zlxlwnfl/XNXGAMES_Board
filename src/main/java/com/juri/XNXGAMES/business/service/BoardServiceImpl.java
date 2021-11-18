package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.entity.BoardEntity;
import com.juri.XNXGAMES.business.exception.BoardException;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	BoardRepository boardRepository;

	@Override
	public Long getBoardId(@NonNull final String type, @NonNull final String subType) {
		Optional<BoardEntity> boardEntityOptional = boardRepository.findByTypeAndSubType(type, subType);

		if(boardEntityOptional.isPresent()) {
			return boardEntityOptional.get().getId();
		}
		else {
			throw new BoardException(HttpStatus.NOT_FOUND, "board not exist");
		}
	}

	@Override
	public List<Long> getBoardIdList(@NonNull final long boardId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
