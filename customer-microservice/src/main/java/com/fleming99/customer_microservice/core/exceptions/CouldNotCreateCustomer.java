package com.fleming99.customer_microservice.core.exceptions;

import lombok.extern.slf4j.Slf4j;

public class CouldNotCreateCustomer extends RuntimeException {

    public CouldNotCreateCustomer(String message) {
        super(message);
    }

    public CouldNotCreateCustomer(String message, Throwable cause) {
        super(message, cause);
    }
}
