package ru.collbox.service;

import ru.collbox.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto, Long userId);

    AccountDto updateAccount(AccountDto accountDto, Long userId, Long accId);

    List<AccountDto> getAccountsByIdUser(Long userId);
}
