package com.juri.XNXGAMES.business.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class PostGetResponseDTO {

	private final long postId;
	@NonNull
	private final String postType;
	@NonNull
	private final String writerId;
	private final int commentCount;
	@NonNull
	private final String regdate;
	@NonNull
	private final String title;
	@NonNull
	private final String content;
	private final int hits;
	private final int heartCount;
	@NonNull
	private final List<String> gameTagList;
	
}