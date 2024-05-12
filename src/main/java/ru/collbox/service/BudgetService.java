package ru.collbox.service;

import ru.collbox.dto.ExpenditureBudgetDto;

public interface BudgetService {
    ExpenditureBudgetDto getBudget(Long accId, Long userId);
}
