package com.juri.XNXGAMES.business.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class PostGetListDTO {
	
	private final Long postId;
	private final String postType;
	private final String writerId;
	private final int commentCount;
	private final String regdate;
	private final String title;
	private final int hits;
	private final int heartCount;
	
}