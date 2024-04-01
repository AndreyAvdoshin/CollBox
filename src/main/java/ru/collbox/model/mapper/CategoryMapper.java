package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}
