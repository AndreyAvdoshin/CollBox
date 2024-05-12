package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность DTO счёта")
public class AccountDto {
    //@Schema(description = "Идентификатор счёта", example = "1") //настройка аннотации для отображения имени в swagger
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) //настройка аннотации для скрытия поля в swagger
    private Long id;

    @Schema(description = "Название счёта", example = "Любое удобное название")
    @NotBlank
    private String title;

    @Schema(description = "Баланс счёта", example = "20305.55")
    @Min(0)
    private BigDecimal balance = BigDecimal.ZERO;
}
