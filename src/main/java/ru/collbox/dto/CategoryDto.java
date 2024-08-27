package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.utils.CategoryType;

@Data
@NoArgsConstructor
@Schema(description = "Сущность DTO категории")
public class CategoryDto {
    @Schema(description = "Id категории", example = "1")
    @Min(0)
    private Long id;

    @Schema(description = "Имя категории", example = "Любое удобное имя")
    @NotBlank
    private String title;

    @Schema(description = "Тип категории", example = "MANDATORY")
    private CategoryType categoryType;
}
