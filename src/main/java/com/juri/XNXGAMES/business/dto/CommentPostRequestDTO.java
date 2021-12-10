package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@RequiredArgsConstructor
public class CommentPostRequestDTO {

	@NonNull
	@NotBlank
	private final String writerId;
	@NonNull
	@NotBlank
	private final String content;
	
}
