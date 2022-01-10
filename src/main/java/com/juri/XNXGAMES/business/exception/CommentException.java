package com.juri.XNXGAMES.business.exception;

import com.juri.XNXGAMES.global.exception.CustomException;
import com.juri.XNXGAMES.global.exception.ErrorCode;

public class CommentException extends CustomException {

    public CommentException(ErrorCode errorCode) {
        super(errorCode);
    }

}
