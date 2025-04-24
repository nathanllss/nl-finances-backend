package com.nathan.nl_finances.services;

import com.nathan.nl_finances.config.security.UserContext;
import com.nathan.nl_finances.controllers.dtos.TransactionDto;
import com.nathan.nl_finances.mapper.TransactionMapper;
import com.nathan.nl_finances.model.Transaction;
import com.nathan.nl_finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

//    public TransactionListDto getAllTransactions(final String accountId, Pageable pageable) {
//        UUID uuidAccountId = UUID.fromString(accountId);
//        Page<TransactionMinDto> transactions = transactionRepository.searchTransactionsByAccountId(uuidAccountId, pageable);
//        return new TransactionListDto(uuidAccountId, transactions);
//    }
    public Page<TransactionDto> getAllTransactions(Pageable pageable) {
        var accountId = UserContext.getCurrentUser().getAccountId();
        var result = transactionRepository.findByOwner_Id(accountId, pageable);
        return result.map(this::toDto);
    }


//    public TransactionMinDto createTransaction(final TransactionDto transactionDto) {
//
//    }

    private TransactionDto toDto(Transaction transaction) {
        return TransactionMapper.toDto(transaction);
    }

}
