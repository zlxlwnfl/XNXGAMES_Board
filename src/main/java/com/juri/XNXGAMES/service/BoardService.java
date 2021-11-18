package com.juri.XNXGAMES.service;

import java.util.List;

public interface BoardService {

	Long getBoardId(String type, String subType);
	List<Long> getBoardIdList(Long boardId);
	
}
