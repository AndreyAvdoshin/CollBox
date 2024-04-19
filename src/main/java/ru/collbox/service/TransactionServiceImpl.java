package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.TransactionType;
import ru.collbox.dto.AccountDto;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.UpdateTransactionDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.exception.NotOwnerException;
import ru.collbox.model.Account;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.TransactionRepository;

import java.time.LocalDateTime;

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
        //TODO при создании транзакции нужно отнять/прибавить от account +/- amount, вызвать метод обновления account 'updateAccount' для обновления счёта
        recalculationAccount(transactionDto, userId);

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
    public TransactionFullDto updateTransactionByUserId(UpdateTransactionDto updateTransactionDto, Long userId,
                                                        Long transId) {
        //TODO при обновлении выполняется переасчёт Account
        recalculationAccount(new TransactionDto(null,null,
                        updateTransactionDto.getAccountId(), null,
                        updateTransactionDto.getAmount(),
                        updateTransactionDto.getTransactionType(),
                        updateTransactionDto.isActive()), userId);

        Transaction transaction = getTransactionBelongUser(userId, transId);

        transaction = mapper.updateTransaction(transaction, updateTransactionDto);
        updateTransaction(updateTransactionDto, transaction, userId);
        transaction.setUpdated(LocalDateTime.now());
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

    private void recalculationAccount(TransactionDto transactionDto, Long userId){
        Account account = accountService.returnIfExists(userId, transactionDto.getAccountId());

        if(transactionDto.getTransactionType().equals(TransactionType.EXPENSE)){
            account.setBalance(account.getBalance() - transactionDto.getAmount());
            accountService.updateAccount(new AccountDto(null,null, account.getBalance()), userId, transactionDto.getAccountId());
        } else {
            account.setBalance(account.getBalance() + transactionDto.getAmount());
            accountService.updateAccount(new AccountDto(null,null, account.getBalance()), userId, transactionDto.getAccountId());
        }
    }
}
