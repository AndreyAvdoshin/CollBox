package ru.collbox.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.CategoryDto;
import ru.collbox.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Long userId){
        log.info("Запрос создания категории - {}, {}", categoryDto, userId);
        return service.createCategory(categoryDto, userId);
    }

    @SneakyThrows
    @PatchMapping("/{userId}/{catId}")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto,
                              @PathVariable long userId, @PathVariable long catId) {
        log.info("Запрос обновления категории  categoryDto {}, userId {}, catId {}", categoryDto, userId, catId);
        return service.updateCategory(categoryDto, userId, catId);
    }

    @GetMapping("/{userId}")
    public List<CategoryDto> getCategorysByIdUser(@PathVariable long userId) {
        log.info("Запрос получение всех категорий пользователя по индикатору userId = {}", userId);
        return service.getCategorysByIdUser(userId);
    }
}
