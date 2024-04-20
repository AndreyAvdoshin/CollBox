package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
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
public class TransferController {
    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public TransferFullDto createTransfer(@RequestBody @Valid TransferDto transferDto,
                                          @PathVariable @Positive Long userId){
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transferDto, userId);
        return service.createTransfer(transferDto, userId);
    }

    @PatchMapping("/{transfId}")
    public TransferFullDto updateTransfer(@RequestBody @Valid UpdateTransferDto updateTransferDto,
                                          @PathVariable @Positive Long userId,
                                          @PathVariable @Positive Long transfId){
        log.info("Запрос на обновление трансфера - {}, по id - {}, пользователем по id - {}",
                updateTransferDto, transfId, userId);
        return service.updateTransferByUserId(updateTransferDto, userId, transfId);
    }
}
