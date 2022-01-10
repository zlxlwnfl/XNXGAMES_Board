package com.juri.XNXGAMES.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    SERVER_CANNOT_SAVE(HttpStatus.INTERNAL_SERVER_ERROR, "server can't save"),
    SERVER_CANNOT_SEND_MESSAGE(HttpStatus.INTERNAL_SERVER_ERROR, "server can't send message to message queue"),

    // Board
    BOARD_NOT_EXIST(HttpStatus.NOT_FOUND, "board not exist"),

    // Post
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "post not exist"),

    // Comment
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "comment not exist"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

}
