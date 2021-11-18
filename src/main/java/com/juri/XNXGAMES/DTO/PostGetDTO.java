package com.juri.XNXGAMES.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostGetDTO {
	
	private final Long postId;
	private final String postType;
	private final String writerId;
	private final int commentCount;
	private final String regdate;
	private final String title;
	private final String content;
	private final int hits;
	private final int heartCount;
	private final List<String> gameTagList;
	
}