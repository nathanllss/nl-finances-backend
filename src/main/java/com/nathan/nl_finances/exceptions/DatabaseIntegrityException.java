package com.nathan.nl_finances.exceptions;

public class DatabaseIntegrityException extends RuntimeException {

    public DatabaseIntegrityException() {
        super();
    }
    public DatabaseIntegrityException(String message) {
        super(message);
    }
    public DatabaseIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
