package com.example.retailrwdcustomcalc.exceptionhandlers;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer doesn't exist " + id );
    }
}
