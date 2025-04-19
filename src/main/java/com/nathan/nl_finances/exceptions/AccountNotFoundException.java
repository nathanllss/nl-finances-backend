package com.nathan.nl_finances.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super();
    }
    public AccountNotFoundException(String message) {
        super(message);
    }
    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
