package com.nathan.nl_finances.projections;

import com.nathan.nl_finances.domain.enums.BudgetPeriod;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BudgetMinDto {

    String getId();
    String getName();
    BigDecimal getPlannedAmount();
    BigDecimal getSpentAmount();
    BudgetPeriod getPeriod();
    LocalDate getStartDate();
    LocalDate getEndDate();
}
