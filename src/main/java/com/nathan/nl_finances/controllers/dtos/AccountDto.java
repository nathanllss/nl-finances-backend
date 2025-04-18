package com.nathan.nl_finances.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AccountDto {

    private final UUID id;
    private final BigDecimal currentBalance;
}