package ru.collbox.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.utils.CategoryType;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    @NotBlank
    private String title;

    private CategoryType categoryType;
}
