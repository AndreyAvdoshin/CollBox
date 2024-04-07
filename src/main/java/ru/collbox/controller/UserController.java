package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.UserDto;
import ru.collbox.service.UserService;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Запрос создания пользователя - {}", userDto);
        return service.createUser(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody @Valid UserDto userDto,
                              @PathVariable @Positive Long userId) {
        log.info("Запрос обновления пользователя updateUser userDto {}, userId {}", userDto, userId);
        return service.updateUser(userDto, userId);
    }

    @GetMapping("/{userId}")
    public UserDto getByIdUser(@PathVariable @Positive Long userId) {
        log.info("Запрос получение пользователя по индикатору userId = {}", userId);
        return service.getByIdUser(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive Long userId) {
        log.info("Запрос удаления пользователя по индикатору userId = {}", userId);
        service.deleteUser(userId);
    }
}
