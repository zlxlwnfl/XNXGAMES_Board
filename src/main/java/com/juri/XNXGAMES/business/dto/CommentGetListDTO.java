package com.juri.XNXGAMES.business.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class CommentGetListDTO {

	private final Long commentId;
	private final String writerId;
	private final String regdate;
	private final String content;
	private final int heartCount;
	
}
