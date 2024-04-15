package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.UpdateTransactionDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.exception.NotOwnerException;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.TransactionRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

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
    public TransactionFullDto createTransaction(TransactionDto transactionDto, Long userId) {
        Transaction transaction = mapper.toTransaction(transactionDto);

        transaction.setUser(userService.returnIfExists(userId));
        transaction.setCategory(transactionDto.getCategoryId() != null ?
                categoryService.returnIfExists(userId, transactionDto.getCategoryId()) : null);
        transaction.setAccount(accountService.returnIfExists(userId, transactionDto.getAccountId()));
        transaction = repository.save(transaction);

        TransactionFullDto transactionFullDto = mapper.toTransactionFullDto(transaction);

        log.info("Создание транзакции - {}", transactionFullDto);

        return transactionFullDto;
    }
    @Transactional
    @Override
    public TransactionFullDto updateTransactionByUserId(UpdateTransactionDto transactionDto, Long userId,
                                                        Long transId) {
        Transaction transaction = getTransactionBelongUser(userId, transId);

        transaction = mapper.updateTransaction(transaction, transactionDto);
        updateTransaction(transactionDto, transaction, userId);
        transaction = repository.save(transaction);
        
        log.info("Обновление транзакции - {}", transaction);
        return mapper.toTransactionFullDto(transaction);
    }

    private Transaction getTransactionBelongUser(Long userId, Long transId) {
        Transaction transaction = returnIfExists(transId);
        userService.checkExistingUser(userId);
        if (!transaction.getUser().getId().equals(userId)) {
            throw new NotOwnerException("Транзакция по id - " + transId +
                    " не принадлежит пользователю по id - " + userId);
        }
        return transaction;
    }

    private Transaction returnIfExists(Long transId) {
        return repository.findById(transId)
                .orElseThrow(() -> new NotFoundException("Transaction", transId));
    }

    private void updateTransaction(UpdateTransactionDto transactionDto, Transaction transaction, Long userId) {
        transaction.setAccount(transactionDto.getAccountId() != null ?
                accountService.returnIfExists(userId, transactionDto.getAccountId()) : transaction.getAccount());

        transaction.setCategory(transactionDto.getCategoryId() != null ?
                categoryService.returnIfExists(userId, transactionDto.getCategoryId()) : transaction.getCategory());
    }
}
