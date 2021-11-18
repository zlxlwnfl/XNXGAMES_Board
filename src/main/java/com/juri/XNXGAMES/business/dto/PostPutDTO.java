package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class PostPutDTO {

	@NonNull
	private final String title;
	@NonNull
	private final String content;
	@NonNull
	private final String writerId;
	@NonNull
	private final String postType;
	@NonNull
	private final List<String> gameTagList;
	
}
