package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;

import java.util.List;

public interface BoardService {

	Long getBoard(BoardDTO boardDTO);
	List<BoardGetListDTO> getBoardList();
	BoardEntity insertBoard(BoardDTO boardDTO);
	void modifyBoard(long boardId, BoardDTO boardDTO);
	void deleteBoard(long boardId);
	
}
