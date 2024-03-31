package ru.collbox.controller;

import jakarta.validation.Valid;
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
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        log.info("Запрос создания пользователя - {}", userDto);
        return service.createUser(userDto);
    }

    @GetMapping("/{userId}")
    public UserDto getByIdUser(@PathVariable long userId) {
        log.info("Запрос получение пользователя по инфидфикатору userId = {}", userId);
        return service.getByIdUser(userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userId) {
        log.info("Запрос удаления пользователя по индификатору userId = {}", userId);
        service.deleteUser(userId);
    }
}
