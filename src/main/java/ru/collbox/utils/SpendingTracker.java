package ru.collbox.utils;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@NoArgsConstructor
public class SpendingTracker {

    private BigDecimal dailyLimit;

    public void setDailyLimit(BigDecimal amount, LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        dailyLimit = amount.divide(BigDecimal.valueOf(daysInMonth), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public BigDecimal getRemainingDailyLimit(BigDecimal amount, int days) {
        return null;
    };
}
