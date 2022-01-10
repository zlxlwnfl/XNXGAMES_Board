package com.juri.XNXGAMES.business.dto;

import com.juri.XNXGAMES.business.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

public class BoardDTO {

    @Getter
    @ToString
    @RequiredArgsConstructor
    static public class Criterial {
        private final int currentPageNum;
        private final int amountData;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    static public class Request {
        @NonNull
        @NotBlank
        private final String type;
        @NonNull
        @NotBlank
        private final String subType;
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    @Builder
    static public class Response {
        private final long boardId;
        @NonNull
        private final String type;
        @NonNull
        private final String subType;

        static public BoardDTO.Response fromBoard(@NonNull final Board board) {
            return BoardDTO.Response.builder()
                    .boardId(board.getBoardId())
                    .type(board.getType())
                    .subType(board.getSubType())
                    .build();
        }
    }

}
