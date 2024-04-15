package ru.collbox.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferFullDto {
    private Long id;
    private AccountDto sourceAccount;
    private AccountDto destinationAccount;
    private String description;
    private Double amount;
    private LocalDateTime transferDate;
}
