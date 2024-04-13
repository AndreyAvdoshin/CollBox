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
public class TransactionFullDto {
    private Long id;

    @Positive
    private CategoryDto category;

    @NotNull
    @Positive
    private AccountDto account;
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    private TransactionType transactionType;
    private boolean active = true;
}
