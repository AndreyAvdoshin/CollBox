package ru.collbox.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
    @NotBlank
    private String title;
    private Double balance;
}
