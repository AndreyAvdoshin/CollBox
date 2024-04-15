package ru.collbox.service;

import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;

public interface TransferService {
    TransferFullDto createTransfer(TransferDto transferDto, Long userId);
}
