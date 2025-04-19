package com.nathan.nl_finances.services;

import com.nathan.nl_finances.exceptions.AccountNotFoundException;
import com.nathan.nl_finances.repositories.AccountRepository;
import com.nathan.nl_finances.repositories.TransactionRepository;
import com.nathan.nl_finances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public BigDecimal getAccountBalanceById(final UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"))
                .getCurrentBalance();
    }
    @Transactional
    public void deleteAccountById(final UUID id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account not found");
        }
        accountRepository.deleteById(id);
        userService.createAccount(userRepository.findByAccount_Id(id));
    }
}
