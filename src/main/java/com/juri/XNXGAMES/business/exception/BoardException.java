package com.juri.XNXGAMES.business.exception;

public class BoardException extends CustomException {

    public BoardException(ErrorCode errorCode) {
        super(errorCode);
    }

}
