package com.juri.XNXGAMES.business.exception;

import org.springframework.http.HttpStatus;

public class PostException extends CustomException {

    public PostException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public PostException(HttpStatus httpStatus, String message, Throwable cause) {
        super(httpStatus, message, cause);
    }

    public PostException(HttpStatus httpStatus, Throwable cause) {
        super(httpStatus, cause);
    }
}
