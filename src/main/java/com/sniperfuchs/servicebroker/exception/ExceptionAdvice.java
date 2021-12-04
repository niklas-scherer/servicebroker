package com.sniperfuchs.servicebroker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;



//TODO move all exception handling to this advice class

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MaintenanceInfoConflictException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public @ResponseBody
    Map<String,Object> handleIndexNotFoundException(MaintenanceInfoConflictException bre,
                                                    HttpServletRequest request, HttpServletResponse resp) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("error", "MaintenanceInfoConflict");
        result.put("error_message", bre.getMessage());
        return result;
    }

    //TODO: Maybe use advice for everything so it can get logged as well
}
