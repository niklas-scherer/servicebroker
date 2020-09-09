package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceBindingNotFoundException extends RuntimeException
{
    public ServiceBindingNotFoundException(String message)
    {
        super(message);
    }
}
