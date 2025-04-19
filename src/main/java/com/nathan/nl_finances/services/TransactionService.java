package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.TransactionListDto;
import com.nathan.nl_finances.controllers.dtos.TransactionMinDto;
import com.nathan.nl_finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionListDto getAllTransactions(String accountId, Pageable pageable) {

        UUID uuidAccountId = UUID.fromString(accountId);
        Page<TransactionMinDto> transactions = transactionRepository.searchTransactionsByAccountId(uuidAccountId, pageable);
        return new TransactionListDto(uuidAccountId, transactions);
    }

}
