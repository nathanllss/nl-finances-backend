package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.projections.TransactionMinDto;
import com.nathan.nl_finances.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<Page<TransactionMinDto>> getMyTransactions(@AuthenticationPrincipal UserDetails userDetails,
                                                                     Pageable pageable) {
        var transactions = transactionService.getAllMyTransactions(
                ((User) userDetails).getAccount().getId(),
                pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailsDto> getTransactionById(@PathVariable String id) {
        var transaction = transactionService.getTransactionById(UUID.fromString(id));
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{accountId}/all")
    public ResponseEntity<Page<TransactionMinDto>> getAllTransactionsByUser_AccountId(@PathVariable String accountId, Pageable pageable) {
        var transactions = transactionService.getAllTransactionsByUser_AccountId(UUID.fromString(accountId), pageable);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@AuthenticationPrincipal UserDetails userDetails,
                                                               @RequestBody TransactionDetailsDto transactionDto) {
        var transaction = transactionService.createTransaction(
                ((User) userDetails).getAccount(),
                transactionDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(location).body("Transaction created successfully");
    }
}
