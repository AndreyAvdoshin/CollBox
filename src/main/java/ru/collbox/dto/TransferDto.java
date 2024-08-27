package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO трансфер (не полный)")
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
    private BigDecimal amount;
    private LocalDateTime transferDate;
}
