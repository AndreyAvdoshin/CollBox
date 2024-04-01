package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.TransactionRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.INSTANCE.toTransaction(transactionDto);
        log.info("Создание транзакции - {}", transaction);
        transaction = repository.save(transaction);
        return TransactionMapper.INSTANCE.toTransactionDto(transaction);
    }
}
