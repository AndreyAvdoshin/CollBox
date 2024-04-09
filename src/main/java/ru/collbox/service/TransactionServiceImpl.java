package ru.collbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.AccountRepository;
import ru.collbox.repository.CategoryRepository;
import ru.collbox.repository.TransactionRepository;
import ru.collbox.repository.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final UserService userService;
    //private final CategoryRepository categoryRepository;
    //private final AccountRepository accountRepository;

    public TransactionServiceImpl(UserService userService, TransactionMapper transactionMapper,
                                  TransactionRepository repository) {
        this.userService = userService;
        this.mapper = transactionMapper;
        this.repository = repository;
    }

    @Transactional
    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto, Long userId) {
        Transaction transaction = mapper.toTransaction(transactionDto);
        transaction.setUser(userService.returnIfExists(userId));
        //transaction.setCategory(categoryRepository.findById(transactionDto.getCategory()).get());
        //transaction.setAccount(accountRepository.findById(transactionDto.getAccount()).get());
        transaction = repository.save(transaction);

        log.info("Создание транзакции - {}", transaction);
        return mapper.toTransactionDto(transaction);
    }
}
