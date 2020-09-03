package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingServiceInstanceAttributeMismatchException extends RuntimeException
{
    public ExistingServiceInstanceAttributeMismatchException(String message) {
        super(message);
    }
}
