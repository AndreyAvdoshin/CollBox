package ru.collbox.service;

import org.springframework.stereotype.Service;
import ru.collbox.dto.DaySpendDto;
import ru.collbox.model.Account;
import ru.collbox.utils.SpendingTracker;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SpendingServiceImpl implements SpendingService {

    private final AccountService accountService;
    private final SpendingTracker tracker;

    public SpendingServiceImpl(AccountService accountService, SpendingTracker tracker) {
        this.accountService = accountService;
        this.tracker = tracker;
    }

    @Override
    public DaySpendDto getDaysLimit(Long accId, Long userId) {
        Account account = accountService.returnIfExists(userId, accId);
        tracker.setDailyLimit(account.getBalance(), LocalDate.now());
        BigDecimal dailyLimit = tracker.getDailyLimit();

        return new DaySpendDto(LocalDate.now(), dailyLimit);
    }
}
