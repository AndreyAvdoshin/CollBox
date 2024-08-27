package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO дневные расходы")
public class DaySpendDto {
    private LocalDate date;
    private BigDecimal dailyLimit;
}
