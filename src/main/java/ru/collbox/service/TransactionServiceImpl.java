package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.TransactionType;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.UpdateTransactionDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.exception.NotOwnerException;
import ru.collbox.model.Account;
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
        //TODO при создании транзакции нужно отнять/прибавить от account +/- amount, вызвать метод обновления account 'updateAccount' для обновления счёта
        Transaction transaction = mapper.toTransaction(transactionDto);

        transaction.setUser(userService.returnIfExists(userId));
        transaction.setCategory(transactionDto.getCategoryId() != null ?
                categoryService.returnIfExists(userId, transactionDto.getCategoryId()) : null);
        transaction.setAccount(accountService.returnIfExists(userId, transactionDto.getAccountId()));
        transaction = repository.save(transaction);

        recalculationAccount(transaction, userId);

        TransactionFullDto transactionFullDto = mapper.toTransactionFullDto(transaction);

        log.info("Создание транзакции - {}", transactionFullDto);

        return transactionFullDto;
    }

    @Override
    public TransactionFullDto getTransactionByUserId(Long userId, Long transId) {
        Transaction transaction = getTransactionBelongUser(userId, transId);

        log.info("Получение транзакции - {}", transaction);
        return mapper.toTransactionFullDto(transaction);
    }

    @Transactional
    @Override
    public TransactionFullDto updateTransactionByUserId(UpdateTransactionDto updateTransactionDto, Long userId,
                                                        Long transId) {
        //TODO при обновлении выполняется переасчёт Account

        Transaction transaction = getTransactionBelongUser(userId, transId);
        // отменяем предыдущее действие
        recalculationAccountToDelete(transaction, userId);

        transaction = mapper.updateTransaction(transaction, updateTransactionDto);
        updateTransaction(updateTransactionDto, transaction, userId);

        transaction = repository.save(transaction);
        recalculationAccount(transaction, userId);
        
        log.info("Обновление транзакции - {}", transaction);
        return mapper.toTransactionFullDto(transaction);
    }

    @Transactional
    @Override
    public void deleteTransaction(Long userId, Long transId) {
        Transaction transaction = getTransactionBelongUser(userId, transId);
        recalculationAccountToDelete(transaction, userId);

        log.info("Удаление транзакции - {}", transaction);
        repository.delete(transaction);
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

    private void recalculationAccount(Transaction transaction, Long userId) {
        Account account = accountService.returnIfExists(userId, transaction.getAccount().getId());

        /*TODO добавить проверку, если на балансе нет денег, то потритить их нельзя???
          или если трата больше, чем остаток на балансе
         */

        if (transaction.getTransactionType().equals(TransactionType.EXPENSE)) {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
            accountService.updateAccount(account);
        } else {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
            accountService.updateAccount(account);
        }
    }

    private void recalculationAccountToDelete(Transaction transaction, Long userId) {
        Account account = accountService.returnIfExists(userId,
                transaction.getAccount().getId());

        if (transaction.getTransactionType().equals(TransactionType.EXPENSE)) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
            accountService.updateAccount(account);
        } else {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
            accountService.updateAccount(account);
        }
    }
}
