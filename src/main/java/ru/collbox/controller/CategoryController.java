package ru.collbox.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.collbox.dto.CategoryDto;
import ru.collbox.service.CategoryService;

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
        log.info("Запрос создания категори - {}", categoryDto);
        return service.createCategory(categoryDto);
    }
}
