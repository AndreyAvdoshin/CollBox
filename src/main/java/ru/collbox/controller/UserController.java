package ru.collbox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.AuthRequest;
import ru.collbox.dto.AuthResponse;
import ru.collbox.dto.UserDto;
import ru.collbox.service.UserService;


@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователь", description = "API для работы с пользователем")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/auth")
    public AuthResponse authentication(@RequestBody @Valid AuthRequest request) {
        return service.authenticate(request);
    }

    @Operation(
            summary = "Создание пользователя",
            description = "Метод создания пользователя, принимает на вход UserDto."
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Запрос создания пользователя - {}", userDto);
        return service.createUser(userDto);
    }

    @Operation(
            summary = "Обновление пользователя",
            description = "Метод обновление пользователя, принимает на вход UserDto и id пользователя."
    )
    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto,
                              @PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос обновления пользователя updateUser userDto {}, userId {}", userDto, userId);
        return service.updateUser(userDto, userId);
    }

    @Operation(
            summary = "Получение пользователя",
            description = "Метод получения пользователя, принимает на вход id пользователя."
    )
    @GetMapping("/{userId}")
    public UserDto getByIdUser(@PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос получение пользователя по индикатору userId = {}", userId);
        return service.getByIdUser(userId);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Метод удаление пользователя, принимает на вход id пользователя."
    )
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос удаления пользователя по индикатору userId = {}", userId);
        service.deleteUser(userId);
    }
}
