package ru.collbox.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransferDto {
    @Positive
    @NotNull
    private Long sourceAccountId;
    @Positive
    @NotNull
    private Long destinationAccountId;
    private String description;
    @NotNull
    private BigDecimal amount;
}
