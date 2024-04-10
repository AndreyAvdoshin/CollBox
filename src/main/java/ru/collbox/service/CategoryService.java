package ru.collbox.service;

import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto, Long userId);

    List<CategoryDto> getCategoriesByIdUser(Long userId);

    CategoryDto updateCategory(CategoryDto categoryDto, Long userId, Long catId);

    CategoryDto getCategoryById(Long userId, Long catId);

    Category returnIfExists(Long userId, Long catId);
}
