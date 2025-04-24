package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.controllers.dtos.TransactionDto;
import com.nathan.nl_finances.services.TransactionService;
import com.nathan.nl_finances.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/users/{userId}/account/{accountId}/transactions")
@RequestMapping("/api/v1/users/transactions")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    @Autowired
    private UserService userService;

//    @GetMapping()
//    public ResponseEntity<TransactionListDto> getTransactions(@PathVariable String accountId, Pageable pageable) {
//        TransactionListDto transactions = transactionService.getAllTransactions(accountId, pageable);
//        return ResponseEntity.ok(transactions);
//    }
    @GetMapping()
    public ResponseEntity<Page<TransactionDto>> getTransactions(Pageable pageable) {
        var transactions = transactionService.getAllTransactions(pageable);
        return ResponseEntity.ok(transactions);
    }

//    @PostMapping
//    public ResponseEntity<TransactionListDto> createTransaction(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) {
//        userService.getMe();
//        TransactionDto createdTransaction = transactionService.createTransaction(transactionDto);
//        return ResponseEntity.ok(createdTransaction);
//    }
}
