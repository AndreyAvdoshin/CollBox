package ru.collbox.controller;

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
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("{accId}/budgets")
    public ExpenditureBudgetDto getBudget(@PathVariable @Positive Long userId, @PathVariable @Positive Long accId) {
        log.info("Запрос на получение бюджета счета по id - {} пользователем по id - {}", accId, userId);
        return budgetService.getBudget(accId, userId);
    }
}
