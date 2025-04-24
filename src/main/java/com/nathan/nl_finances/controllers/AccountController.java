package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.dtos.AccountDto;
import com.nathan.nl_finances.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/account/{accountId}" )
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String accountId) {
        AccountDto account = accountService.getAccountById(UUID.fromString(accountId));
        return ResponseEntity.ok(account);
    }

    @PutMapping
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String accountId, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.updateAccount(UUID.fromString(accountId), accountDto);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccountById(UUID.fromString(accountId));
        return ResponseEntity.noContent().build();
    }


}
