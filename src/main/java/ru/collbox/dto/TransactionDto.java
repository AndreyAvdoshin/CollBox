package ru.collbox.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.TransactionType;

@Data
@NoArgsConstructor
public class TransactionDto {
    private Long id;

    @NotNull
    @Positive
    private Long categoryId;

    @NotNull
    @Positive
    private Long accountId;
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    private TransactionType transactionType;
    private boolean active = true;
}
