package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.utils.TransactionType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO транзакции (не полная)")
public class TransactionDto {
    private Long id;

    @Positive
    private Long categoryId;

    @NotNull
    @Positive
    private Long accountId;
    private String description;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private TransactionType transactionType;
    private boolean active = true;
}
