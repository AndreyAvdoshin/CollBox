package ru.collbox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.TransactionType;
import ru.collbox.model.Account;
import ru.collbox.model.Category;
import ru.collbox.model.User;

@Data
@NoArgsConstructor
public class TransactionDto {
    private Long user;
    private Long category;
    private Long account;
    private String description;
    private Double amount;
    private TransactionType transactionType;
    private boolean active;
}
