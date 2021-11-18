package com.juri.XNXGAMES.business.exception;

import org.springframework.http.HttpStatus;

public class CommentException extends CustomException {

    public CommentException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public CommentException(HttpStatus httpStatus, String message, Throwable cause) {
        super(httpStatus, message, cause);
    }

    public CommentException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, cause);
    }

}
