package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.AccountDto;
import ru.collbox.dto.DaySpendDto;
import ru.collbox.service.AccountService;
import ru.collbox.service.SpendingService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/{userId}/accounts")
public class AccountController {
    private final AccountService service;
    private final SpendingService spendingService;

    public AccountController(AccountService service, SpendingService spendingService) {
        this.service = service;
        this.spendingService = spendingService;
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody @Valid AccountDto accountDto, @PathVariable @Positive Long userId) {
        log.info("Запрос создания счёта - {}, пользователем с id - {}", accountDto, userId);
        return service.createAccount(accountDto, userId);
    }

    @GetMapping("/{accId}")
    public AccountDto getAccount(@PathVariable @Positive Long accId, @PathVariable @Positive Long userId) {
        log.info("Запрос на получение кошелька по id - {}, пользователем с id - {}", accId, userId);
        return service.getAccountById(userId, accId);
    }

    @GetMapping
    public List<AccountDto> getAccountsByIdUser(@PathVariable @Positive Long userId) {
        log.info("Запрос получение всех счётов пользователя по id - {}", userId);
        return service.getAccountsByIdUser(userId);
    }

    @GetMapping("/{accId}/limit")
    public DaySpendDto getDailyLimit(@PathVariable @Positive Long userId, @PathVariable @Positive Long accId) {
        log.info("Запрос на получение лимита счета по id - {} пользователем по id - {}", accId, userId);
        return spendingService.getDaysLimit(accId, userId);
    }

    @PatchMapping("/{accId}")
    public AccountDto updateAccount(@RequestBody AccountDto accountDto,
                                    @PathVariable Long userId, @PathVariable @Positive Long accId) {
        log.info("Запрос обновления счёта - {} по id - {}, пользователем с id - {}", accountDto, accId, userId);
        return service.updateAccount(accountDto, userId, accId);
    }

    @DeleteMapping("/{accId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long userId, @PathVariable @Positive Long accId) {
        log.info("Запрос на удаления счета по id - {}, пользователем с id - {}", accId, userId);
        service.deleteAccountById(userId, accId);
    }
}
