package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.utils.TransactionType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность DTO транзакция (обновления)")
public class UpdateTransactionDto {
    private Long categoryId;
    private Long accountId;
    private String description;
    private BigDecimal amount;
    private TransactionType transactionType;
    private boolean active;
}
