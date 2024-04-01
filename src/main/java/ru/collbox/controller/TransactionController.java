package ru.collbox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transactionDto) {
        log.info("Запрос создания транзакции - {}", transactionDto);
        return null;//service.createTransaction(transactionDto);
    }
}
