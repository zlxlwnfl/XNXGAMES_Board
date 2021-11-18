package com.juri.XNXGAMES.business.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BoardToMemberPostMessage implements Serializable {

	private final String type; // create, delete
	private final String memberId;
	private final Long postId;
	
}
