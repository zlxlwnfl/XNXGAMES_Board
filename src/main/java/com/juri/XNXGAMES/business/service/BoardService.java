package com.juri.XNXGAMES.business.service;

import java.util.List;

public interface BoardService {

	Long getBoardId(String type, String subType);
	List<Long> getBoardIdList(long boardId);
	
}
