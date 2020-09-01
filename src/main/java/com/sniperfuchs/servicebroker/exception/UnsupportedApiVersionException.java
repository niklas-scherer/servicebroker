package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class UnsupportedApiVersionException extends RuntimeException
{
    public UnsupportedApiVersionException(String message) {
        super(message);
    }
}
