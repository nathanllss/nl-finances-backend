package com.nathan.nl_finances.services;

import com.nathan.nl_finances.controllers.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.exceptions.TransactionNotFoundException;
import com.nathan.nl_finances.mapper.TransactionMapper;
import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.Transaction;
import com.nathan.nl_finances.projections.TransactionMinDto;
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

    public Page<TransactionMinDto> getAllTransactionsByUser_AccountId(final UUID accountId, Pageable pageable) {
        authService.validateSelfOrAdmin(accountId);
        return transactionRepository.searchTransactionsByAccountId(accountId, pageable);
    }

    public Page<TransactionMinDto> getAllMyTransactions(final UUID accountId, Pageable pageable) {
        return transactionRepository.searchByOwner_Id(accountId, pageable);
    }

    public TransactionDetailsDto getTransactionById(final UUID id) {
        if (transactionRepository.existsById(id)) {
            var transaction = validateTransactionOwner(id);
            return toDto(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    public TransactionDetailsDto createTransaction(final Account account, final TransactionDetailsDto transactionDto) {
            Transaction entity = toEntity(transactionDto);
            entity.setOwner(account);
            entity = transactionRepository.saveAndFlush(entity);
            return toDto(entity);
        }


//    public TransactionDetailsDto updateTransaction(final UUID accountId,
//                                                   final UUID transactionId,
//                                                   final TransactionDetailsDto transactionDto ) {
//        var transaction = transactionRepository.findById(transactionId).get();
//        var transactionOwnerId = transaction.getOwner().getId();
//        authService.validateSelfOrAdmin(transactionOwnerId);
//
//
//    }

    private TransactionDetailsDto toDto(Transaction transaction) {
        return TransactionMapper.toDto(transaction);
    }

    private Transaction toEntity(TransactionDetailsDto transactionDto) {
        return TransactionMapper.toEntity(transactionDto);
    }

    private Transaction validateTransactionOwner(final UUID transactionId) {
        var transaction = transactionRepository.findById(transactionId).get();
        var transactionOwnerId = transaction.getOwner().getId();
        authService.validateSelfOrAdmin(transactionOwnerId);
        return transaction;
    }

}
