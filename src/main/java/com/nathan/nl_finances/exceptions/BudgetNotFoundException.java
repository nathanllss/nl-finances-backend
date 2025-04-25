package com.nathan.nl_finances.exceptions;

public class BudgetNotFoundException extends RuntimeException {

    public BudgetNotFoundException() {
        super();
    }
    public BudgetNotFoundException(String message) {
        super(message);
    }
    public BudgetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
