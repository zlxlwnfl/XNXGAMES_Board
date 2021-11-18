package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListDTO;

import java.util.List;

public interface BoardService {

	Long getBoard(String type, String subType);
	List<BoardGetListDTO> getBoardList();
	void insertBoard(BoardDTO boardDTO);
	void modifyBoard(long boardId, BoardDTO boardDTO);
	void deleteBoard(long boardId);
	
}
