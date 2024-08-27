package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collbox.dto.ExpenditureBudgetDto;
import ru.collbox.model.Account;
import ru.collbox.model.User;
import ru.collbox.utils.SpendingTracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Slf4j
public class BudgetServiceImpl implements BudgetService{
    private final AccountService accountService;
    private final UserService userService;
    private final SpendingTracker tracker;

    public BudgetServiceImpl(UserService userService, AccountService accountService, SpendingTracker tracker) {
        this.accountService = accountService;
        this.userService = userService;
        this.tracker = tracker;
    }

    @Override
    public ExpenditureBudgetDto getBudget(Long accId, Long userId) {
        Account account = accountService.returnIfExists(userId, accId);
        User user = userService.returnIfExists(userId);
        ExpenditureBudgetDto expenditureBudgetDto = new ExpenditureBudgetDto();
        expenditureBudgetDto.setAccountId(account.getId());
        expenditureBudgetDto.setTitle(account.getTitle());
        expenditureBudgetDto.setBalance(account.getBalance());
        expenditureBudgetDto.setMandatoryExpenses(tracker.calculation(account.getBalance(), 0.50F));
        expenditureBudgetDto.setSpendingOnDesires(tracker.calculation(account.getBalance(), 0.30F));
        expenditureBudgetDto.setSavings(tracker.calculation(account.getBalance(), 0.20F));

        expenditureBudgetDto.setMandatoryExpensesMonthly(tracker.getDailyLimit(
                expenditureBudgetDto.getMandatoryExpenses(), user.getSalaryDay(), LocalDate.now()));

        expenditureBudgetDto.setSpendingOnDesiresMonthly(tracker.getDailyLimit(
                expenditureBudgetDto.getSpendingOnDesires(), user.getSalaryDay(), LocalDate.now()));

        expenditureBudgetDto.setSavingsMonthly(tracker.getDailyLimit(expenditureBudgetDto.getSavings(),
                user.getSalaryDay(), LocalDate.now()));

        return expenditureBudgetDto;
    }
}
