package ru.collbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;
import ru.collbox.model.mapper.AccountMapper;
import ru.collbox.repository.AccountRepository;
import ru.collbox.repository.UserRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository repository;
    private final UserRepository userRepository;
    private final AccountMapper mapper;

    @Transactional
    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = mapper.toAccount(accountDto);
        //Account account = new Account();
        account.setUser(userRepository.findById(accountDto.getUserId()).get());
        //account.setTitle(accountDto.getTitle());
        //account.setBalance(accountDto.getBalance());

        log.info("Создание счёта - {}", account);
        account = repository.save(account);
        return mapper.toAccountDto(account);
    }
}
