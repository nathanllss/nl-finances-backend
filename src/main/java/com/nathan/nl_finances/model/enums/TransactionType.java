package com.nathan.nl_finances.model.enums;

public enum TransactionType {

    INCOME("INCOME"),
    EXPENSE("EXPENSE"),
    SAVING("SAVING"),
    TRANSFER("TRANSFER");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
