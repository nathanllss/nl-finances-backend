package com.nathan.nl_finances.exceptions;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super();
    }
    public TransactionNotFoundException(String message) {
        super(message);
    }
    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
