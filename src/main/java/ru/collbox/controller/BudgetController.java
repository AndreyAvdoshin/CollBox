package ru.collbox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.collbox.dto.ExpenditureBudgetDto;
import ru.collbox.service.BudgetService;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/{userId}/")
@Tag(name = "Бюджет", description = "API для работы с бюджетом")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @Operation(
            summary = "Получение бюджета",
            description = "Метод получения бюджета владельца который запрашивает, принимает на вход id счёта и id владельца счёта." +
                    " Бюджет рассчитывается на три вида Основной, Желаний, Копилка. " +
                    " Расчёт определяется на месяц и на день в зависимости от количества дней в месяце."
    )
    @GetMapping("{accId}/budgets")
    public ExpenditureBudgetDto getBudget(@PathVariable @Positive @Parameter(description = "Id владельца счёта") Long userId,
                                          @PathVariable @Positive @Parameter(description = "Id счёта") Long accId) {
        log.info("Запрос на получение бюджета счета по id - {} пользователем по id - {}", accId, userId);
        return budgetService.getBudget(accId, userId);
    }
}
