package com.juri.XNXGAMES.business.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BoardToMemberPostMessage implements Serializable {

	@NonNull
	private final String type; // create, delete
	@NonNull
	private final String memberId;
	private final long postId;
	
}
