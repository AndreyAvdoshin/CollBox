package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;
import ru.collbox.service.TransferService;

@Slf4j
@Validated
@RestController
@RequestMapping("users/{userId}/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public TransferFullDto createTransfer(@RequestBody @Valid TransferDto transferDto,
                                          @PathVariable @Positive Long userId){
        log.info("Запрос создания транзакции - {}, пользователем по id - {}", transferDto, userId);
        return transferService.createTransfer(transferDto, userId);
    }
}
