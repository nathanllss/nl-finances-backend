package com.nathan.nl_finances.exceptions;

public class InvalidInformation extends RuntimeException {

    public InvalidInformation() {
        super();
    }
    public InvalidInformation(String message) {
        super(message);
    }
    public InvalidInformation(String message, Throwable cause) {
        super(message, cause);
    }
}
