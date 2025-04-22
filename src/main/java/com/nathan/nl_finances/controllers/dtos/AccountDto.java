package com.nathan.nl_finances.controllers.dtos;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class AccountDto {

    UUID id;
    BigDecimal currentBalance;
    List<CategoryDto> categories;
    List<BudgetDto> budgets;
}