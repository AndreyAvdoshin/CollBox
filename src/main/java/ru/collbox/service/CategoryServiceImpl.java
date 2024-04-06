package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;
import ru.collbox.model.mapper.CategoryMapper;
import ru.collbox.repository.CategoryRepository;
import ru.collbox.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;
    private final UserRepository userRepository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, UserRepository userRepository, CategoryMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto, Long userId) {
        //проверка категории на дублирование имени
        Category checkTitle = repository.findByUser_IdAndTitle(userId, categoryDto.getTitle());
        if(checkTitle != null){
            throw new RuntimeException(String.format("Category с таким же именем %s, уже существует!", checkTitle.getTitle()));
        }

        Category category = mapper.toCategory(categoryDto, userId);
        category.setUser(userRepository.findById(userId).get());
        log.info("Создание категории - {}", category);
        category = repository.save(category);
        return mapper.toCategoryDto(category);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long userId, Long catId){
        //проверка категории на дублирование имени
        Category checkTitle = repository.findByUser_IdAndTitle(userId, categoryDto.getTitle());
        if(checkTitle != null){
            throw new RuntimeException(String.format("Category с таким же именем %s, уже существует!", checkTitle.getTitle()));
        }

        //проверка на принадлежность категории юзеру
        Category category = repository.findByIdAndUser_Id(catId, userId);
        if(category == null){
            throw new RuntimeException(String.format("Category с таким id {} и пользователем {} не существует!", catId, userId ));
        }

        category = mapper.updateCategory(category, categoryDto);
        log.info("Обновление категории - {}", category);
        category = repository.save(category);
        return mapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategorysByIdUser(Long userId){
        List<Category> categories = repository.findAllByUser_Id(userId);
        return categories.stream()
                .map(mapper::toCategoryDto)
                .collect(Collectors.toList());
    }
}
