package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingServiceBindingAttributeMismatchException extends RuntimeException
{
    public ExistingServiceBindingAttributeMismatchException(String message)
    {
        super(message);
    }
}
