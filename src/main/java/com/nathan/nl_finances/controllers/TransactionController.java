package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.TransactionListDto;
import com.nathan.nl_finances.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/{userId}/account/{accountId}/transactions")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<TransactionListDto> getTransactions(@PathVariable String accountId, Pageable pageable) {
        TransactionListDto transactions = transactionService.getAllTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
