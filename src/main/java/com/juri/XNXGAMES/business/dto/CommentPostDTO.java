package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentPostDTO {

	@NonNull
	private final String writerId;
	@NonNull
	private final String content;
	
}
