package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class PostPostRequestDTO {

	private final long postId;
	@NonNull
	@NotBlank
	private final String title;
	@NonNull
	@NotBlank
	private final String content;
	@NonNull
	@NotBlank
	private final String writerId;
	@NonNull
	@NotBlank
	private final String postType;
	@NonNull
	private final List<String> gameTagList;
	
}
