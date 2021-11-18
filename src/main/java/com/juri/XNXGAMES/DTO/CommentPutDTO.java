package com.juri.XNXGAMES.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentPutDTO {

	private final Long postId;
	private final Long commentId;
	private final String writerId;
	private final String content;
	
}
