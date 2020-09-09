package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ServiceBindingGoneException extends RuntimeException
{
    public ServiceBindingGoneException(String message)
    {
        super(message);
    }
}
