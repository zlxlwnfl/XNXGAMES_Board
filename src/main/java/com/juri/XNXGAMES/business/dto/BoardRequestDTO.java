package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardRequestDTO {

	@NonNull
	private final String boardType;
	@NonNull
	private final String boardSubType;
	
}
