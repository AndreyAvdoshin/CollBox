package ru.collbox.service;

import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;
import ru.collbox.dto.UpdateTransferDto;

public interface TransferService {
    TransferFullDto createTransfer(TransferDto transferDto, Long userId);

    TransferFullDto updateTransferByUserId(UpdateTransferDto updateTransferDto, Long userId, Long transfId);

    void deleteTransfer(Long userId, Long transfId);
}
