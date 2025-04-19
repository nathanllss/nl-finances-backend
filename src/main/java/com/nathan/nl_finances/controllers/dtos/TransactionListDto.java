package com.nathan.nl_finances.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TransactionListDto {

    private UUID accountId;
    private List<TransactionMinDto> transactions;
}
