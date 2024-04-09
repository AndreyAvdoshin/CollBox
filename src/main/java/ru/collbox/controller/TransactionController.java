package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.TransactionDto;
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
    public TransactionDto createTransaction(@RequestBody @Valid TransactionDto transactionDto,
                                            @PathVariable @Positive Long userId) {
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transactionDto, userId);
        return service.createTransaction(transactionDto, userId);
    }
}
