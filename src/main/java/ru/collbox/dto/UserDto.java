package ru.collbox.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Сущность DTO пользователь")
public class UserDto {
    @Schema(description = "Id пользователя", example = "1")
    @Min(0)
    private Long id;

    @Schema(description = "Имя пользователя", example = "Иван Иванов Иванович")
    @NotBlank
    private String name;

    @Schema(description = "Email пользователя", example = "ivan@ya.ru")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Число долучения ЗП")
    private Integer salaryDay = 1;

    @Schema(description = "Пароль пользователя", example = "jgds483JFSH-&?^%$#@!)(+=_")
    @NotBlank
    private String password;
}
