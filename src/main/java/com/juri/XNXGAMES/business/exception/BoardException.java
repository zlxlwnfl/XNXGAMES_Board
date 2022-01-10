package com.juri.XNXGAMES.business.exception;

import com.juri.XNXGAMES.global.exception.CustomException;
import com.juri.XNXGAMES.global.exception.ErrorCode;

public class BoardException extends CustomException {

    public BoardException(ErrorCode errorCode) {
        super(errorCode);
    }

}
