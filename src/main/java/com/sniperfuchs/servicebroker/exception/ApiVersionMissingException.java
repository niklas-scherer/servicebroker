package com.sniperfuchs.servicebroker.exception;

public class ApiVersionMissingException extends RuntimeException{
    public ApiVersionMissingException(String message) {
        super(message);
    }
}
