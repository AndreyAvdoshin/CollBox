package ru.collbox.service;

import org.springframework.stereotype.Service;
import ru.collbox.dto.DaySpendDto;
import ru.collbox.model.Account;
import ru.collbox.model.User;
import ru.collbox.utils.SpendingTracker;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SpendingServiceImpl implements SpendingService {
    private final AccountService accountService;
    private final UserService userService;
    private final SpendingTracker tracker;

    public SpendingServiceImpl(UserService userService, AccountService accountService, SpendingTracker tracker) {
        this.accountService = accountService;
        this.userService = userService;
        this.tracker = tracker;
    }

    @Override
    public DaySpendDto getDaysLimit(Long accId, Long userId) {
        Account account = accountService.returnIfExists(userId, accId);
        User user = userService.returnIfExists(userId);

        BigDecimal dailyLimit = tracker.getDailyLimit(account.getBalance(), user.getSalaryDay(), LocalDate.now());

        return new DaySpendDto(LocalDate.now(), dailyLimit);
    }
}
