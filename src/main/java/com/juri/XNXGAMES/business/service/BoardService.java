package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.BoardRequestDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;

import java.util.List;

public interface BoardService {

	Long getBoard(BoardRequestDTO boardRequestDTO);
	List<BoardGetListResponseDTO> getBoardList();
	BoardEntity insertBoard(BoardRequestDTO boardRequestDTO);
	void updateBoard(long boardId, BoardRequestDTO boardRequestDTO);
	void deleteBoard(long boardId);
	
}
