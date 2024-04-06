package ru.collbox.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.AccountDto;
import ru.collbox.service.AccountService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public AccountDto createAccount(@Valid @RequestBody AccountDto accountDto,@PathVariable Long userId){
        log.info("Запрос создания счёта - {}, {}", accountDto, userId);
        return service.createAccount(accountDto, userId);
    }

    @SneakyThrows
    @PatchMapping("/{userId}/{accId}")
    public AccountDto updateAccount(@RequestBody AccountDto accountDto,
                                      @PathVariable long userId, @PathVariable long accId) {
        log.info("Запрос обновления счёта  accountDto {}, userId {}, accId {}", accountDto, userId, accId);
        return service.updateAccount(accountDto, userId, accId);
    }

    @GetMapping("/{userId}")
    public List<AccountDto> getAccountsByIdUser(@PathVariable long userId) {
        log.info("Запрос получение всех счётов пользователя по индефикатору userId = {}", userId);
        return service.getAccountsByIdUser(userId);
    }
}
