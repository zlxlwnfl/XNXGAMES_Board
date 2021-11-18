package com.juri.XNXGAMES.service;

import java.util.List;

public interface BoardService {

	Long searchBoard(String type, String subType);
	List<Long> getBoard(Long boardId);
	
}
