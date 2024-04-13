package ru.collbox.service;

import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;

public interface TransactionService {

    TransactionFullDto createTransaction(TransactionDto transactionDto, Long userId);
}
