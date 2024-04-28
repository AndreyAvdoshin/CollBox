package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.AccountDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.model.Account;
import ru.collbox.model.mapper.AccountMapper;
import ru.collbox.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final UserService userService;
    private final AccountMapper mapper;

    public AccountServiceImpl(AccountRepository repository, UserService userService, AccountMapper mapper) {
        this.repository = repository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public AccountDto createAccount(AccountDto accountDto, Long userId){
        Account account = mapper.toAccount(accountDto);
        account.setUser(userService.returnIfExists(userId));

        log.info("Создание счёта - {}", account);
        account = repository.save(account);
        return mapper.toAccountDto(account);
    }

    @Transactional
    @Override
    public AccountDto updateAccount(AccountDto accountDto, Long userId, Long accId) {
        Account account = returnIfExists(userId, accId);

        account = mapper.updateAccount(account, accountDto);
        log.info("Обновление счёта - {}", account);
        account = repository.save(account);
        return mapper.toAccountDto(account);
    }

    @Override
    public AccountDto getAccountById(Long userId, Long accId) {
        Account account = returnIfExists(userId, accId);

        log.info("Получение кошелька - {}", account);
        return mapper.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccountsByIdUser(Long userId) {
        List<Account> accounts = repository.findAllByUserId(userId);
        return accounts.stream()
                .map(mapper::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Long userId, Long accId) {
        Account account = returnIfExists(userId, accId);
        repository.delete(account);
    }

    @Override
    public Account returnIfExists(Long userId, Long accId) {
        return repository.findByIdAndUserId(accId, userId)
                .orElseThrow(() -> new NotFoundException("Account", accId));
    }

    @Override
    public void updateAccount(Account account) {
        repository.save(account);
    }

}
