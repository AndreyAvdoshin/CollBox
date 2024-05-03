package ru.collbox.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@NoArgsConstructor
@Component
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
