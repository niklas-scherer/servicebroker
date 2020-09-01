package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler
{
    /*
    @ExceptionHandler({UnsupportedApiVersionException.class, ApiVersionMissingException.class})
    protected ResponseEntity<Object> handlePreconditionFailed(RuntimeException ex,
                                                              WebRequest request)
    {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
        //return new ResponseEntity<>(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

     */
}
