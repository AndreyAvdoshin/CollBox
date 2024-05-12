package ru.collbox.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@NoArgsConstructor
@Component
public class SpendingTracker {

    public BigDecimal getDailyLimit(BigDecimal amount, LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        return amount.divide(BigDecimal.valueOf(daysInMonth), 2, RoundingMode.HALF_UP);
    }
    public BigDecimal calculation(BigDecimal decimal, float percent){
        return decimal.multiply(BigDecimal.valueOf(percent)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRemainingDailyLimit(BigDecimal amount, int days) {
        return null;
    };
}
