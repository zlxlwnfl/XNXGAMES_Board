package com.juri.XNXGAMES.business.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardRequestDTO {

	@NonNull
	@NotBlank
	private final String boardType;
	@NonNull
	@NotBlank
	private final String boardSubType;
	
}
