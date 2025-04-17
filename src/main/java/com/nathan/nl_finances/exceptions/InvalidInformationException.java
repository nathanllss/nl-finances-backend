package com.nathan.nl_finances.exceptions;

public class InvalidInformationException extends RuntimeException {

    public InvalidInformationException() {
        super();
    }
    public InvalidInformationException(String message) {
        super(message);
    }
    public InvalidInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
