package com.juri.XNXGAMES.business.exception;

import org.springframework.http.HttpStatus;

public class BoardException extends CustomException {

    public BoardException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public BoardException(HttpStatus httpStatus, String message, Throwable cause) {
        super(httpStatus, message, cause);
    }

    public BoardException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, cause);
    }
}
