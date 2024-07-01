package com.fleming99.customer_microservice.core.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(String message) {
        super(message);
    }

    public CustomerNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
