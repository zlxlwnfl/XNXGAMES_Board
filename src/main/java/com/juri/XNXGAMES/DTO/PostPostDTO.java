package com.juri.XNXGAMES.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class PostPostDTO {

	private final Long postId;
	private final String title;
	private final String content;
	private final String writerId;
	private final String postType;
	private final List<String> gameTagList;
	
}
