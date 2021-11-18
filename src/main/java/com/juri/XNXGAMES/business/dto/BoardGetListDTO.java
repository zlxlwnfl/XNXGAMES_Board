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
public class BoardGetListDTO {

    private final long boardId;
    @NonNull
    private final String boardType;
    @NonNull
    private final String boardSubType;

}
