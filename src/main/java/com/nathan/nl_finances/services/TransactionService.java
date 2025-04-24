package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.controllers.dtos.TransactionListDto;
import com.nathan.nl_finances.exceptions.TransactionNotFoundException;
import com.nathan.nl_finances.mapper.TransactionMapper;
import com.nathan.nl_finances.model.Transaction;
import com.nathan.nl_finances.model.projections.TransactionMinDto;
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

    @Autowired
    private AuthService authService;

    public TransactionListDto getAllTransactionsByUser_AccountId(final UUID accountId, Pageable pageable) {
        Page<TransactionMinDto> transactions = transactionRepository.searchTransactionsByAccountId(accountId, pageable);
        return new TransactionListDto(accountId, transactions);
    }

    public Page<TransactionMinDto> getAllTransactionsOfUser(final UUID accountId, Pageable pageable) {
        return transactionRepository.searchByOwner_Id(accountId, pageable);
    }

    public TransactionDetailsDto getTransactionById(final UUID id) {
        if (transactionRepository.existsById(id)) {
            var transaction = transactionRepository.findById(id).get();
            var transactionOwnerId = transaction.getOwner().getId();
            authService.validateSelfOrAdmin(transactionOwnerId);
            return toDto(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }


//    public TransactionMinDto createTransaction(final TransactionDto transactionDto) {
//
//    }

    private TransactionDetailsDto toDto(Transaction transaction) {
        return TransactionMapper.toDto(transaction);
    }

}
