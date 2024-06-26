package ru.collbox.service;

import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto, Long userId);

    AccountDto updateAccount(AccountDto accountDto, Long userId, Long accId);

    List<AccountDto> getAccountsByIdUser(Long userId);

    AccountDto getAccountById(Long userId, Long accId);

    void deleteAccountById(Long userId, Long accId);

    Account returnIfExists(Long userId, Long accId);

    void updateAccount(Account account);
}
