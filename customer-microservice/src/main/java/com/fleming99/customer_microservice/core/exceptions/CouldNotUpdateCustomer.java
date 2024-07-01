package com.fleming99.customer_microservice.core.exceptions;

public class CouldNotUpdateCustomer extends RuntimeException{

    public CouldNotUpdateCustomer(String message) {
        super(message);
    }

    public CouldNotUpdateCustomer(String message, Throwable cause) {
        super(message, cause);
    }
}
