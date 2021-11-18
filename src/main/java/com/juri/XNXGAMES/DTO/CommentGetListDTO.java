package com.juri.XNXGAMES.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentGetListDTO {

	private final Long commentId;
	private final String writerId;
	private final String regdate;
	private final String content;
	private final int heartCount;
	
}
