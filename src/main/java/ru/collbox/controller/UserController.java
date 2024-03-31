package ru.collbox.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
