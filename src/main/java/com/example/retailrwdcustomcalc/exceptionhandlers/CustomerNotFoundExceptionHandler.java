package com.example.retailrwdcustomcalc.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String customerNotFoundException(RuntimeException ex, WebRequest request) {
        return ex.getMessage();
    }
}
