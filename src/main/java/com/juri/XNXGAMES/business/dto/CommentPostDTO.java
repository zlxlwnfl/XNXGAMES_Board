package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentPostDTO {

	private final String writerId;
	private final String content;
	
}
