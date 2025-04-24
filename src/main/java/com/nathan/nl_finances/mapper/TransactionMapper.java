package com.nathan.nl_finances.mapper;

import com.nathan.nl_finances.domain.entity.Transaction;
import com.nathan.nl_finances.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.projections.TransactionMinDto;


public abstract class TransactionMapper {

    public static TransactionDetailsDto toDto(Transaction transaction) {
        return new TransactionDetailsDto(
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

    public static Transaction toEntity(TransactionDetailsDto transactionDetailsDto) {
        Transaction entity = new Transaction();
        entity.setTitle(transactionDetailsDto.getTitle());
        entity.setDescription(transactionDetailsDto.getDescription());
        entity.setCategory(CategoryMapper.toEntity(transactionDetailsDto.getCategory()));
        entity.setType(transactionDetailsDto.getType());
        entity.setMoment(transactionDetailsDto.getMoment());
        entity.setValue(transactionDetailsDto.getValue());
        entity.setRecurring(transactionDetailsDto.isRecurring());
        return entity;
    }


    public static TransactionMinDto toMinDto(Transaction transaction) {
        return new TransactionMinDto.TransactionMinDtoImpl(
                transaction.getId().toString(),
                transaction.getTitle(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getMoment(),
                transaction.getValue(),
                transaction.isRecurring()
        );
    }
}