package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.controllers.dtos.TransactionDto;
import com.nathan.nl_finances.model.Transaction;


public abstract class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getOwner().getId(),
                transaction.getTitle(),
                transaction.getDescription(),
                CategoryMapper.toDto(transaction.getCategory()),
                transaction.getType(),
                transaction.getMoment(),
                transaction.getValue(),
                transaction.isRecurring()
        );
    }



}