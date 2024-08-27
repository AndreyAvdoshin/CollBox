package ru.collbox.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
@Tag(name = "Категория", description = "API для работы с категорией")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(
            summary = "Создание категории",
            description = "Метод создания категории, принимает на вход CategoryDto и id пользователя который создаёт категорию"
    )
    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CategoryDto categoryDto,
                                      @PathVariable @Positive @Parameter(description = "Id пользователя") Long userId) {
        log.info("Запрос создания категории - {}, пользователем с id - {}", categoryDto, userId);
        return service.createCategory(categoryDto, userId);
    }

    @Operation(
            summary = "Получение категорий",
            description = "Метод получения списка категорий по индефикатору владельца, принимает на вход id владельца категории"
    )
    @GetMapping
    public List<CategoryDto> getCategoriesByIdUser(@PathVariable @Parameter(description = "Id владельца") Long userId) {
        log.info("Запрос получение всех категорий пользователя по id - {}", userId);
        return service.getCategoriesByIdUser(userId);
    }

    @Operation(
            summary = "Получение категории",
            description = "Метод получения категории по индефикатору категории и владельца, принимает на вход id категории и id владельца категории"
    )
    @GetMapping("/{catId}")
    private CategoryDto getCategoryById(@PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                                        @PathVariable @Positive @Parameter(description = "Id категории") Long catId) {
        log.info("Получение категории по id - {} пользователем с id - {}", catId, userId);
        return service.getCategoryById(catId, userId);
    }

    @Operation(
            summary = "Обновление категории",
            description = "Метод обновления категории по индефикатору категории и владельца, принимает на вход id категории и id владельца категории"
    )
    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto,
                                      @PathVariable @Positive @Parameter(description = "Id владельца") Long userId,
                                      @PathVariable @Positive @Parameter(description = "Id категории") Long catId) {
        log.info("Запрос обновления категории - {} по id - {}, пользователем по id - {}", categoryDto, catId, userId);
        return service.updateCategory(categoryDto, userId, catId);
    }

}
