package ru.collbox.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@NoArgsConstructor
@Component
public class SpendingTracker {

    public BigDecimal getDailyLimit(BigDecimal amount, Integer salaryDay, LocalDate date) {
        int daysInMonth = date.lengthOfMonth();
        int dayOfMonth = date.getDayOfMonth();
        int countDaysToSalary = 0;

        if (dayOfMonth > salaryDay) {
            countDaysToSalary = (daysInMonth - dayOfMonth) + salaryDay;
        } else if (dayOfMonth < salaryDay) {
            countDaysToSalary = salaryDay - dayOfMonth;
        }

        return amount.divide(BigDecimal.valueOf(countDaysToSalary), 2, RoundingMode.HALF_UP);
    }
    public BigDecimal calculation(BigDecimal decimal, float percent){
        return decimal.multiply(BigDecimal.valueOf(percent)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRemainingDailyLimit(BigDecimal amount, int days) {
        return null;
    };
}
