package ru.collbox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Транзакция", description = "API для работы с транзакцией")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Operation(
            summary = "Создание транзакции",
            description = "Метод создания транзакции, принимает на вход TransactionDto и id пользователя который создаёт транзакцию"
    )
    @PostMapping
    public TransactionFullDto createTransaction(@RequestBody @Valid TransactionDto transactionDto,
                                                @PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transactionDto, userId);
        return service.createTransaction(transactionDto, userId);
    }

    @Operation(
            summary = "Обновление транзакции",
            description = "Метод обновления транзакции, принимает на вход UpdateTransactionDto," +
                    " id пользователя который обновляет и является владельцем транзакции, " +
                    "id обновляемой транзакции"
    )
    @PatchMapping("/{transId}")
    public TransactionFullDto updateTransaction(@RequestBody @Valid UpdateTransactionDto transactionDto,
                                                @PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                                                @PathVariable @Positive @Parameter(description = "Id транзакции") Long transId) {
        log.info("Запрос на обновление транзакции - {}, по id - {}, пользователем по id - {}",
                transactionDto, transId, userId);
        return service.updateTransactionByUserId(transactionDto, userId, transId);
    }

    @Operation(
            summary = "Удаление транзакции",
            description = "Метод удаления транзакции, " +
                    "принимает на вход id пользователя который удаляет и является владельцем транзакции, " +
                    "id удаляемой транзакции"
    )
    @DeleteMapping("/{transId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                                  @PathVariable @Positive @Parameter(description = "Id транзакции") Long transId) {
        log.info("Запрос на удаление транзакции по id - {} пользователем по id - {}", transId, userId);
        service.deleteTransaction(userId, transId);
    }
}
