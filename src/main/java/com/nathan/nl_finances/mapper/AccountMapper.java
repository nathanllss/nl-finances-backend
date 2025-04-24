package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.controllers.dtos.AccountDto;
import com.nathan.nl_finances.domain.entity.Account;


public abstract class AccountMapper {

    public static AccountDto toDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getCurrentBalance(),
                account.getCategories()
                        .stream().map(CategoryMapper::toDto).toList(),
                account.getBudgets()
                        .stream().map(BudgetMapper::toDto).toList()
        );
    }



}