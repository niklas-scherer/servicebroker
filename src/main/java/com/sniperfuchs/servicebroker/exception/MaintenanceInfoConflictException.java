package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MaintenanceInfoConflictException extends RuntimeException
{
    public MaintenanceInfoConflictException(String message) {
        super(message);
    }
}
