package ru.collbox.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.CategoryDto;
import ru.collbox.service.CategoryService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/users/{userId}/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CategoryDto categoryDto,
                                      @PathVariable @Positive Long userId) {
        log.info("Запрос создания категории - {}, пользователем с id - {}", categoryDto, userId);
        return service.createCategory(categoryDto, userId);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto,
                                      @PathVariable @Positive Long userId, @PathVariable @Positive Long catId) {
        log.info("Запрос обновления категории - {} по id - {}, пользователем по id - {}", categoryDto, catId, userId);
        return service.updateCategory(categoryDto, userId, catId);
    }

    @GetMapping
    public List<CategoryDto> getCategoriesByIdUser(@PathVariable Long userId) {
        log.info("Запрос получение всех категорий пользователя по id - {}", userId);
        return service.getCategoriesByIdUser(userId);
    }
}
