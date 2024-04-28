package ru.collbox.service;

import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.UpdateTransactionDto;

public interface TransactionService {

    TransactionFullDto createTransaction(TransactionDto transactionDto, Long userId);

    TransactionFullDto updateTransactionByUserId(UpdateTransactionDto transactionDto, Long userId, Long transId);

    void deleteTransaction(Long userId, Long transId);
}
