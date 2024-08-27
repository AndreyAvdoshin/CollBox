package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO бюджета расходов")
public class ExpenditureBudgetDto {
    private Long accountId;
    private String title;
    private BigDecimal balance;
    private BigDecimal mandatoryExpenses; //Обязательыне расходы
    private BigDecimal spendingOnDesires; //расходы на желания
    private BigDecimal savings;           //Накопления
    private BigDecimal mandatoryExpensesMonthly; //Обязательыне расходы на месяц
    private BigDecimal spendingOnDesiresMonthly; //расходы на желания на месяц
    private BigDecimal savingsMonthly;           //Накопления на месяц
}
