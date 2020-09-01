package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceInstanceNotFoundException extends RuntimeException
{
    public ServiceInstanceNotFoundException(String message)
    {
        super(message);
    }
}
