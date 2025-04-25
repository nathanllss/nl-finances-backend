package com.nathan.nl_finances.services;

import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.dtos.AccountDto;
import com.nathan.nl_finances.exceptions.AccountNotFoundException;
import com.nathan.nl_finances.mapper.AccountMapper;
import com.nathan.nl_finances.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthService authService;

    @Transactional
    public AccountDto createAccount(User user) {
        user.setActive(true);
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.ZERO);
        user.setAccount(account);
        accountRepository.save(account);
        return AccountMapper.toDto(account);
    }

    @Transactional(readOnly = true)
    public AccountDto getAccountById(final UUID id) {
        var account = validateAccountOwner(id);
        return this.accountToDto(account);
    }


    @Transactional
    public AccountDto updateAccount(final UUID id, final AccountDto accountDto) {
        var account = this.validateAccountOwner(id);
        this.updateAccountData(account, accountDto);
        account = accountRepository.save(account);

        return this.accountToDto(account);
    }

//    @Transactional
//    public void deleteAccountById(final UUID id) {
//        if (!accountRepository.existsById(id)) {
//            throw new AccountNotFoundException("Account not found");
//        }
//        accountRepository.deleteById(id);
//        this.createAccount(userRepository.findByAccount_Id(id));
//    }

    private void updateAccountData(Account account, AccountDto accountDto) {
        account.setCurrentBalance(accountDto.getCurrentBalance());
    }

    private Account validateAccountOwner(final UUID accountId) {
        if (accountRepository.existsById(accountId)) {
            var account = accountRepository.findById(accountId).get();
            var accId = account.getId();
            authService.validateSelfOrAdmin(accId);
            return account;
        } else {
            throw new AccountNotFoundException("Account not found");
        }
    }


    private AccountDto accountToDto(Account account) {
        return AccountMapper.toDto(account);
    }
}