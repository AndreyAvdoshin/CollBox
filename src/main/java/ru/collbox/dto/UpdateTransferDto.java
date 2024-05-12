package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO трансфер (обновление)")
public class UpdateTransferDto {
    @Positive
    @NotNull
    private Long sourceAccountId;
    @Positive
    @NotNull
    private Long destinationAccountId;
    private String description;
    @NotNull
    private BigDecimal amount;
}
