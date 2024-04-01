package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;
import ru.collbox.model.mapper.CategoryMapper;
import ru.collbox.repository.CategoryRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);
        log.info("Создание категории - {}", category);
        category = repository.save(category);
        return CategoryMapper.INSTANCE.toCategoryDto(category);
    }
}
