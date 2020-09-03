package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MaintenanceConflictException extends RuntimeException
{
    public MaintenanceConflictException(String message) {
        super(message);
    }
}
