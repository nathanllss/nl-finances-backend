package com.nathan.nl_finances.controllers.dtos;

import com.nathan.nl_finances.model.enums.BudgetPeriod;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Value
public class BudgetDto {

    String id;
    String accountOwnerId;
    String name;
    BigDecimal plannedAmount;
    BigDecimal spentAmount;
    BudgetPeriod period;
    LocalDate startDate;
    LocalDate endDate;
    Set<CategoryDto> categories = new HashSet<>();

    public void addCategories(CategoryDto category) {
        this.categories.add(category);
    }

}
