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
import ru.collbox.dto.AccountDto;
import ru.collbox.dto.DaySpendDto;
import ru.collbox.service.AccountService;
import ru.collbox.service.SpendingService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/{userId}/accounts")
@Tag(name = "Счёт", description = "API для работы со счётами")
public class AccountController {
    private final AccountService service;
    private final SpendingService spendingService;

    public AccountController(AccountService service, SpendingService spendingService) {
        this.service = service;
        this.spendingService = spendingService;
    }

    @Operation(
            summary = "Создание счёта",
            description = "Метод создания счёта, принимает на вход AccountDto и id пользователя который создаёт счёт"
    )
    @PostMapping
    public AccountDto createAccount(@RequestBody @Valid AccountDto accountDto,
                                    @PathVariable @Positive
                                    @Parameter (description = "Id пользователя") Long userId) {
        log.info("Запрос создания счёта - {}, пользователем с id - {}", accountDto, userId);
        return service.createAccount(accountDto, userId);
    }

    @Operation(
            summary = "Получение счёта",
            description = "Метод получения счёта владельца который запрашивает, принимает на вход id счёта и id владельца счёта"
    )
    @GetMapping("/{accId}")
    public AccountDto getAccount(@PathVariable @Positive @Parameter(description = "Id счёта") Long accId,
                                 @PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId) {
        log.info("Запрос на получение кошелька по id - {}, пользователем с id - {}", accId, userId);
        return service.getAccountById(userId, accId);
    }

    @Operation(
            summary = "Получение счетов",
            description = "Метод получения списка счетов владельца который запрашивает, принимает на вход id владельца счетов"
    )
    @GetMapping
    public List<AccountDto> getAccountsByIdUser(@PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId) {
        log.info("Запрос получение всех счётов пользователя по id - {}", userId);
        return service.getAccountsByIdUser(userId);
    }

    @Operation(
            summary = "Получение лимита счёта",
            description = "Метод получения лимита счёта владельца который запрашивает, принимает на вход id счёта и  id владельца счёта"
    )
    @GetMapping("/{accId}/limit")
    public DaySpendDto getDailyLimit(@PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId,
                                     @PathVariable @Positive @Parameter(description = "Id счёта") Long accId) {
        log.info("Запрос на получение лимита счета по id - {} пользователем по id - {}", accId, userId);
        return spendingService.getDaysLimit(accId, userId);
    }

    @Operation(
            summary = "Обновление счёта",
            description = "Метод обновления указанного счёта владельца который запрашивает, принимает на вход AccountDto, id счёта, id владельца счёта"
    )
    @PatchMapping("/{accId}")
    public AccountDto updateAccount(@RequestBody AccountDto accountDto,
                                    @PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId,
                                    @PathVariable @Positive @Parameter(description = "Id счёта") Long accId) {
        log.info("Запрос обновления счёта - {} по id - {}, пользователем с id - {}", accountDto, accId, userId);
        return service.updateAccount(accountDto, userId, accId);
    }

    @Operation(
            summary = "Удаление счёта",
            description = "Метод удаления указанного счёта владельца который запрашивает, принимает на вход id счёта, id владельца счёта"
    )
    @DeleteMapping("/{accId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId,
                              @PathVariable @Positive @Parameter(description = "Id счёта") Long accId) {
        log.info("Запрос на удаления счета по id - {}, пользователем с id - {}", accId, userId);
        service.deleteAccountById(userId, accId);
    }
}
