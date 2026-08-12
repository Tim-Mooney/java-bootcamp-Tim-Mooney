package com.northstar.crm.exception;

public class InvalidCustomerEventException extends RuntimeException {
    public InvalidCustomerEventException(String customerId) {
        super("Invalid Customer Event " + customerId);
    }
}
