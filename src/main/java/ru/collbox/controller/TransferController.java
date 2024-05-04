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
import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;
import ru.collbox.dto.UpdateTransferDto;
import ru.collbox.service.TransferService;

@Slf4j
@Validated
@RestController
@RequestMapping("users/{userId}/transfers")
@Tag(name = "Трансфер", description = "API для работы с трансфером")
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @Operation(
            summary = "Создание трансфера",
            description = "Метод создания трансфера, принимает на вход TransferDto и id пользователя который создаёт трансфер"
    )
    @PostMapping
    public TransferFullDto createTransfer(@RequestBody @Valid TransferDto transferDto,
                                          @PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transferDto, userId);
        return service.createTransfer(transferDto, userId);
    }

    @Operation(
            summary = "Обновление трансфера",
            description = "Метод обновления трансфера, принимает на вход UpdateTransferDto," +
                    " id владельца трансфера, " +
                    "id обновляемого трансфера"
    )
    @PatchMapping("/{transfId}")
    public TransferFullDto updateTransfer(@RequestBody @Valid UpdateTransferDto updateTransferDto,
                                          @PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                                          @PathVariable @Positive @Parameter(description = "Id трансфера") Long transfId) {
        log.info("Запрос на обновление трансфера - {}, по id - {}, пользователем по id - {}",
                updateTransferDto, transfId, userId);
        return service.updateTransferByUserId(updateTransferDto, userId, transfId);
    }

    @Operation(
            summary = "Удаление трансфера",
            description = "Метод удаления трансфера, принимает на вход " +
                    "id владельца трансфера, " +
                    "id удаляемого трансфера"
    )
    @DeleteMapping("/{transfId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransfer(@PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                               @PathVariable @Positive @Parameter(description = "Id трансфера") Long transfId) {
        log.info("Запрос на удаление трансфера по id - {} пользователем с id - {}", transfId, userId);
        service.deleteTransfer(userId, transfId);
    }
}
