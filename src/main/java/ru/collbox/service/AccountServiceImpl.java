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

import java.util.List;
import java.util.stream.Collectors;

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
    public AccountDto createAccount(AccountDto accountDto, Long userId){
        Account checkTitle = repository.findByUser_IdAndTitle(userId, accountDto.getTitle());
        if (checkTitle != null){
            throw new RuntimeException(String.format("Account с таким же именем %s, уже существует!", checkTitle.getTitle()));
        }

        Account account = mapper.toAccount(accountDto);
        account.setUser(userRepository.findById(userId).get());

        log.info("Создание счёта - {}", account);
        account = repository.save(account);
        return mapper.toAccountDto(account);
    }

    @Transactional
    @Override
    public AccountDto updateAccount(AccountDto accountDto, Long userId, Long accId){
        Account checkTitle = repository.findByUser_IdAndTitle(userId, accountDto.getTitle());
        if (checkTitle != null){
            throw new RuntimeException(String.format("Account с таким же именем %s, уже существует!", checkTitle.getTitle()));
        }

        Account account = repository.findByIdAndUser_Id(accId, userId);
        if(account == null){
            throw new RuntimeException(String.format("Account с таким id {} и пользователем {} не существует!", accId, userId ));
        }
        account = mapper.updateAccount(account, accountDto);
        log.info("Обновление счёта - {}", account);
        account = repository.save(account);
        return mapper.toAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccountsByIdUser(Long userId){
        List<Account> accounts = repository.findAllByUser_Id(userId);
        return accounts.stream()
                .map(mapper::toAccountDto)
                .collect(Collectors.toList());
    }
}
