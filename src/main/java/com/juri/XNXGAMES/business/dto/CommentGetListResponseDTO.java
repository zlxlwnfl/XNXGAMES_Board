package com.juri.XNXGAMES.business.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class CommentGetListResponseDTO {

	private final long commentId;
	@NonNull
	private final String writerId;
	@NonNull
	private final String regdate;
	@NonNull
	private final String content;
	private final int heartCount;
	
}
