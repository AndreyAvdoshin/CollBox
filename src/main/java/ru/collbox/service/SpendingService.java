package ru.collbox.service;

import ru.collbox.dto.DaySpendDto;

public interface SpendingService {

    DaySpendDto getDaysLimit(Long accId, Long userId);
}
