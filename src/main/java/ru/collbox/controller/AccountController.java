package ru.collbox.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.collbox.dto.AccountDto;
import ru.collbox.service.AccountService;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public AccountDto createAccount(@Valid @RequestBody AccountDto accountDto){
        log.info("Запрос создания счёта - {}", accountDto);
        return service.createAccount(accountDto);
    }
}
