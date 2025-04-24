package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.TransactionDetailsDto;
import com.nathan.nl_finances.model.User;
import com.nathan.nl_finances.model.projections.TransactionMinDto;
import com.nathan.nl_finances.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    //    @GetMapping()
//    public ResponseEntity<TransactionListDto> getTransactions(@PathVariable String accountId, Pageable pageable) {
//        TransactionListDto transactions = transactionService.getAllTransactions(accountId, pageable);
//        return ResponseEntity.ok(transactions);
//    }
    @GetMapping()
    public ResponseEntity<Page<TransactionMinDto>> getTransactions(@AuthenticationPrincipal UserDetails userDetails,
                                                                   Pageable pageable) {
        var transactions = transactionService.getAllTransactionsOfUser(
                ((User) userDetails).getAccount().getId(),
                pageable);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailsDto> getTransactionById(@PathVariable String id) {
        var transaction = transactionService.getTransactionById(UUID.fromString(id));
        return ResponseEntity.ok(transaction);
    }

//    @PostMapping
//    public ResponseEntity<TransactionListDto> createTransaction(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) {
//        userService.getMe();
//        TransactionDto createdTransaction = transactionService.createTransaction(transactionDto);
//        return ResponseEntity.ok(createdTransaction);
//    }
}
