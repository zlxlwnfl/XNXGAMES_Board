package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	BoardRepository boardRepository;

	@Override
	public Long getBoardId(@NonNull final String type, @NonNull final String subType) {
		return boardRepository.findByTypeAndSubType(type, subType).get().getId();
	}

	@Override
	public List<Long> getBoardIdList(@NonNull final Long boardId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
