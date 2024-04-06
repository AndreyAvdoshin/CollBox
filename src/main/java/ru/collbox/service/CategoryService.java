package ru.collbox.service;

import ru.collbox.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto, Long userId);

    List<CategoryDto> getCategorysByIdUser(Long userId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long userId, Long catId);
}
