package com.nathan.nl_finances.model.enums;

public enum BudgetPeriod {

    WEEKLY("weekly"),
    MONTHLY("monthly"),
    QUARTERLY("quarterly"),
    SEMESTERLY("semesterly"),
    YEARLY("yearly"),
    CUSTOM("custom");

    private final String value;

    BudgetPeriod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BudgetPeriod fromValue(String value) {
        for (BudgetPeriod period : BudgetPeriod.values()) {
            if (period.value.equalsIgnoreCase(value)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Unknown budget period: " + value);
    }
}
