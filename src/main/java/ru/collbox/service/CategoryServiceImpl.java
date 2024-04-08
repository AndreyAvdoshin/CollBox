package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.CategoryDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.model.Category;
import ru.collbox.model.mapper.CategoryMapper;
import ru.collbox.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final UserService userService;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, UserService userService, CategoryMapper mapper) {
        this.repository = repository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, Long userId) {

        Category category = mapper.toCategory(categoryDto, userId);
        category.setUser(userService.returnIfExists(userId));
        log.info("Создание категории - {}", category);
        category = repository.save(category);
        return mapper.toCategoryDto(category);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long userId, Long catId){

        //проверка на принадлежность категории юзеру
        Category category = returnIfExists(userId, catId);

        category = mapper.updateCategory(category, categoryDto);
        log.info("Обновление категории - {}", category);
        category = repository.save(category);
        return mapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto getCategoryById(Long userId, Long catId) {
        Category category = returnIfExists(userId, catId);

        log.info("Получение категории - {}", category);
        return mapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategoriesByIdUser(Long userId){
        List<Category> categories = repository.findAllByUserId(userId);
        return categories.stream()
                .map(mapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    private Category returnIfExists(Long userId, Long catId) {
        return repository.findByIdAndUserId(catId, userId)
                .orElseThrow(() -> new NotFoundException("Category", catId));
    }
}
