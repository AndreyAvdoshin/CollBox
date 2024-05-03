package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collbox.dto.ExpenditureBudgetDto;
import ru.collbox.model.Account;
import ru.collbox.utils.SpendingTracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Slf4j
public class BudgetServiceImpl implements BudgetService{
    private final AccountService accountService;
    private final SpendingTracker tracker;

    public BudgetServiceImpl(AccountService accountService, SpendingTracker tracker) {
        this.accountService = accountService;
        this.tracker = tracker;
    }

    @Override
    public ExpenditureBudgetDto getBudget(Long accId, Long userId) {
        Account account = accountService.returnIfExists(userId, accId);
        ExpenditureBudgetDto expenditureBudgetDto = new ExpenditureBudgetDto();
        expenditureBudgetDto.setAccountId(account.getId());
        expenditureBudgetDto.setTitle(account.getTitle());
        expenditureBudgetDto.setBalance(account.getBalance());
        expenditureBudgetDto.setMandatoryExpenses(account.getBalance().multiply(BigDecimal.valueOf(0.50))
                .setScale(2, RoundingMode.HALF_UP));
        expenditureBudgetDto.setSpendingOnDesires(account.getBalance().multiply(BigDecimal.valueOf(0.30))
                .setScale(2, RoundingMode.HALF_UP));
        expenditureBudgetDto.setSavings(account.getBalance().multiply(BigDecimal.valueOf(0.20))
                .setScale(2, RoundingMode.HALF_UP));

        tracker.setDailyLimit(expenditureBudgetDto.getMandatoryExpenses(), LocalDate.now());
        expenditureBudgetDto.setMandatoryExpensesMonthly(tracker.getDailyLimit());

        tracker.setDailyLimit(expenditureBudgetDto.getSpendingOnDesires(), LocalDate.now());
        expenditureBudgetDto.setSpendingOnDesiresMonthly(tracker.getDailyLimit());

        tracker.setDailyLimit(expenditureBudgetDto.getSavings(), LocalDate.now());
        expenditureBudgetDto.setSavingsMonthly(tracker.getDailyLimit());

        return expenditureBudgetDto;
    }
}
