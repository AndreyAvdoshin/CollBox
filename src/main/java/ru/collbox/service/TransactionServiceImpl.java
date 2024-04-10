package ru.collbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.TransactionRepository;
import ru.collbox.repository.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final AccountService accountService;

    public TransactionServiceImpl(UserService userService, AccountService accountService,
                                  TransactionMapper transactionMapper, CategoryService categoryService,
                                  TransactionRepository repository) {
        this.userService = userService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.mapper = transactionMapper;
        this.repository = repository;
    }

    @Transactional
    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto, Long userId) {
        Transaction transaction = mapper.toTransaction(transactionDto);

        transaction.setUser(userService.returnIfExists(userId));
        transaction.setCategory(transactionDto.getCategoryId() != null ?
                categoryService.returnIfExists(userId, transactionDto.getCategoryId()) : null);
        transaction.setAccount(accountService.returnIfExists(userId, transactionDto.getAccountId()));
        transaction = repository.save(transaction);

        log.info("Создание транзакции - {}", transaction);
        return mapper.toTransactionDto(transaction);
    }
}
