package com.nathan.nl_finances.controllers.dtos;

import com.nathan.nl_finances.model.projections.TransactionMinDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TransactionListDto {

    private UUID accountId;
    private Page<TransactionMinDto> transactions;
}
