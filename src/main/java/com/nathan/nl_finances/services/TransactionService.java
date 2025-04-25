package com.nathan.nl_finances.services;

import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.Transaction;
import com.nathan.nl_finances.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.exceptions.DatabaseIntegrityException;
import com.nathan.nl_finances.exceptions.TransactionNotFoundException;
import com.nathan.nl_finances.mapper.CategoryMapper;
import com.nathan.nl_finances.mapper.TransactionMapper;
import com.nathan.nl_finances.projections.TransactionMinDto;
import com.nathan.nl_finances.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var transaction = validateTransactionOwner(id);
        return toDto(transaction);
    }

    public TransactionMinDto createTransaction(final Account account, final TransactionDetailsDto transactionDto) {
            Transaction entity = toEntity(transactionDto);
            entity.setOwner(account);
            entity = transactionRepository.saveAndFlush(entity);
            return toMinDto(entity);
        }


    @Transactional
    public TransactionMinDto updateTransaction(final UUID transactionId,
                                                   final TransactionDetailsDto transactionDto) {
        var transaction = validateTransactionOwner(transactionId);
        udpateTransactionData(transaction, transactionDto);
        transaction = transactionRepository.saveAndFlush(transaction);
        return toMinDto(transaction);
    }

    @Transactional
    public void deleteTransaction(final UUID transactionId) {
        try {
            var transaction = validateTransactionOwner(transactionId);
            transaction.setActive(false);
        } catch (Exception e) {
            throw new DatabaseIntegrityException("Database integrity violation");
        }
    }

    private TransactionDetailsDto toDto(Transaction transaction) {
        return TransactionMapper.toDto(transaction);
    }

    private Transaction toEntity(TransactionDetailsDto transactionDto) {
        return TransactionMapper.toEntity(transactionDto);
    }

    private void udpateTransactionData(Transaction transaction, TransactionDetailsDto transactionDto) {
        transaction.setTitle(transactionDto.getTitle());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCategory(CategoryMapper.toEntity(transactionDto.getCategory()));
        transaction.setType(transactionDto.getType());
        transaction.setMoment(transactionDto.getMoment());
        transaction.setValue(transactionDto.getValue());
        transaction.setRecurring(transactionDto.isRecurring());
    }

    private TransactionMinDto toMinDto(Transaction transaction) {
        return TransactionMapper.toMinDto(transaction);
    }

    private Transaction validateTransactionOwner(final UUID transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            var transaction = transactionRepository.findById(transactionId).get();
            var transactionOwnerId = transaction.getOwner().getId();
            authService.validateSelfOrAdmin(transactionOwnerId);
            return transaction;
        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

}
