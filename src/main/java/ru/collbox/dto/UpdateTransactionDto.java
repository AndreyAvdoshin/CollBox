package ru.collbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.TransactionType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransactionDto {
    private Long categoryId;
    private Long accountId;
    private String description;
    private BigDecimal amount;
    private TransactionType transactionType;
    private boolean active;
}
