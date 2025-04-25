package com.nathan.nl_finances.projections;

import java.math.BigDecimal;

public interface AccountSummaryProjection {
    BigDecimal getTotalIncome();
    BigDecimal getTotalSpent();

    String getExpensesByCategoryJson();

}
