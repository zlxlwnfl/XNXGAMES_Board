package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class BoardPostResponseDTO {

    private final long boardId;
    @NonNull
    private final String type;
    @NonNull
    private final String subType;

    static public BoardPostResponseDTO fromBoardEntity(@NonNull final BoardEntity boardEntity) {
        return BoardPostResponseDTO.builder()
                .boardId(boardEntity.getBoardId())
                .type(boardEntity.getType())
                .subType(boardEntity.getSubType())
                .build();
    }

}
