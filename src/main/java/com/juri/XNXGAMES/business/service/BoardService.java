package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.entity.Board;

import java.util.List;

public interface BoardService {

	Long getBoard(BoardDTO.Request boardRequest);
	List<BoardDTO.Response> getBoardList();
	Board insertBoard(BoardDTO.Request boardRequest);
	void updateBoard(long boardId, BoardDTO.Request boardRequest);
	void deleteBoard(long boardId);
	
}
