package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.TransactionListDto;
import com.nathan.nl_finances.controllers.dtos.TransactionMinDto;
import com.nathan.nl_finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionListDto getAllTransactions(String accountId) {

        UUID uuidAccountId = UUID.fromString(accountId);
        List<TransactionMinDto> transactions = transactionRepository.searchTransactionsByAccountId(uuidAccountId);
        return new TransactionListDto(uuidAccountId, transactions);
    }

}
