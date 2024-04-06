package ru.collbox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.TransactionDto;
import ru.collbox.service.TransactionService;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto, @PathVariable Long userId) {
        log.info("Запрос создания транзакции - {}, {}", transactionDto, userId);
        return service.createTransaction(transactionDto, userId);
    }
}
