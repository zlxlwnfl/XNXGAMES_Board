package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardCriteriaDTO {

	private final int currentPageNum;
	private final int amountData;
	
}