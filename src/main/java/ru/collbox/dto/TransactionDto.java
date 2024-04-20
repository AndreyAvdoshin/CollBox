package ru.collbox.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.TransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;

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
