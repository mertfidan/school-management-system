package com.mertfidan.schoolmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HttpMessageNotReadableException extends Exception{
    public HttpMessageNotReadableException(String message) {
        super(message);
    }
}
