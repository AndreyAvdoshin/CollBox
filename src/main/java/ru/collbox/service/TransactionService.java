package ru.collbox.service;

import ru.collbox.dto.TransactionDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
}
