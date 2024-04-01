package ru.collbox.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.collbox.model.User;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long userId;
    @NotBlank
    private String title;
    private Double balance;
}
