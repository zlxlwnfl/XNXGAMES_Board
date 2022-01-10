package com.juri.XNXGAMES.business.exception;

import com.juri.XNXGAMES.global.exception.CustomException;
import com.juri.XNXGAMES.global.exception.ErrorCode;

public class PostException extends CustomException {

    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }

}
