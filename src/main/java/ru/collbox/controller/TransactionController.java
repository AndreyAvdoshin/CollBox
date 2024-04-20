package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.UpdateTransactionDto;
import ru.collbox.service.TransactionService;

@Slf4j
@Validated
@RestController
@RequestMapping("users/{userId}/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public TransactionFullDto createTransaction(@RequestBody @Valid TransactionDto transactionDto,
                                                @PathVariable @Positive Long userId) {
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transactionDto, userId);
        return service.createTransaction(transactionDto, userId);
    }

    @PatchMapping("/{transId}")
    public TransactionFullDto updateTransaction(@RequestBody @Valid UpdateTransactionDto transactionDto,
                                                @PathVariable @Positive Long userId,
                                                @PathVariable @Positive Long transId) {
        log.info("Запрос на обновление транзакции - {}, по id - {}, пользователем по id - {}",
                transactionDto, transId, userId);
        return service.updateTransactionByUserId(transactionDto, userId, transId);
    }

    @DeleteMapping("/{transId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable @Positive Long userId, @PathVariable @Positive Long transId) {
        log.info("Запрос на уладение транзакции по id - {} пользователем по id - {}", transId, userId);
        service.deleteTransaction(userId, transId);
    }
}
