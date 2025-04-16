package com.nathan.nl_finances.model.enums;

public enum CategoryType {

    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String type;

    CategoryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
