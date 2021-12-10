package com.juri.XNXGAMES.business.exception;

public class PostException extends CustomException {

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

}
