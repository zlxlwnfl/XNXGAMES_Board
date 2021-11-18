package com.juri.XNXGAMES.global.config;

import com.juri.XNXGAMES.business.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalWebExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<String> handleCustomException(CustomException e) {
        return new ResponseEntity<>(e.toString(), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
