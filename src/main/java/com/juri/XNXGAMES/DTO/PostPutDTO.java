package com.juri.XNXGAMES.DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostPutDTO {

	private final String boardType;
	private final String boardSubType;
	private final Long postId;
	private final String title;
	private final String content;
	private final String writerId;
	private final String postType;
	private final List<String> gameTagList;
	
}
