package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ServiceInstanceGoneException extends RuntimeException {
    public ServiceInstanceGoneException(String message)
    {
        super(message);
    }
}
