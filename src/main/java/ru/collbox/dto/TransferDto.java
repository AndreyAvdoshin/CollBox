package ru.collbox.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    private Long id;
    @Positive
    @NotNull
    private Long sourceAccountId;
    @Positive
    @NotNull
    private Long destinationAccountId;
    private String description;
    @NotNull
    private Double amount;
    private LocalDateTime transferDate;
}
