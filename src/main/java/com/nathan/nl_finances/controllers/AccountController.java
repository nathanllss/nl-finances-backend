package com.nathan.nl_finances.controllers;

import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.dtos.AccountDto;
import com.nathan.nl_finances.projections.AccountSummaryProjection;
import com.nathan.nl_finances.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account" )
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<AccountDto> getMyAccount(@AuthenticationPrincipal UserDetails loggedUser) {
        AccountDto account = accountService.getAccountById(((User) loggedUser).getId());
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id) {
        AccountDto account = accountService.getAccountById(UUID.fromString(id));
        return ResponseEntity.ok(account);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id, @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.updateAccount(UUID.fromString(id), accountDto);
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/summary")
    public ResponseEntity<AccountSummaryProjection> getAccountSummary(@AuthenticationPrincipal UserDetails loggedUser) {
        return ResponseEntity.ok(accountService.getAccountSummary(((User) loggedUser).getAccount().getId()));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
//        accountService.deleteAccountById(UUID.fromString(id));
//        return ResponseEntity.noContent().build();
//    }


}
