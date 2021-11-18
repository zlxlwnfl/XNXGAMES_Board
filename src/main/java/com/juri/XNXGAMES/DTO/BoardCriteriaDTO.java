package com.juri.XNXGAMES.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardCriteriaDTO {

	private final String boardType;
	private final String boardSubType;
	private final int currentPageNum;
	private final int amountData;
	
}
