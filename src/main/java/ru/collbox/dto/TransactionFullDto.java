package ru.collbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.TransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFullDto {

    private Long id;
    private CategoryDto category;
    private AccountDto account;
    private String description;
    private Double amount;
    private TransactionType transactionType;
    private boolean active = true;
}
