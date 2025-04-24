package com.nathan.nl_finances.services;

import com.nathan.nl_finances.dtos.AccountDto;
import com.nathan.nl_finances.exceptions.AccountNotFoundException;
import com.nathan.nl_finances.mapper.AccountMapper;
import com.nathan.nl_finances.domain.entity.Account;
import com.nathan.nl_finances.domain.entity.User;
import com.nathan.nl_finances.repositories.AccountRepository;
import com.nathan.nl_finances.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

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
        Optional<Account> account = accountRepository.searchByIdWithDetails(id);

        if (account.isEmpty()) {
            throw new AccountNotFoundException("User not found");
        } else {
            return this.accountToDto(account.get());
        }
    }

    @Transactional
    public AccountDto updateAccount(final UUID id, final AccountDto accountDto) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }

        Account entity = account.get();
        this.updateAccountData(account.get(), accountDto);
        entity = accountRepository.save(entity);

        return this.accountToDto(entity);
    }

    @Transactional
    public void deleteAccountById(final UUID id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("Account not found");
        }
        accountRepository.deleteById(id);
        this.createAccount(userRepository.findByAccount_Id(id));
    }

    private void updateAccountData(Account account, AccountDto accountDto) {
        account.setCurrentBalance(accountDto.getCurrentBalance());
    }


    private AccountDto accountToDto(Account account) {
        return AccountMapper.toDto(account);
    }

}
