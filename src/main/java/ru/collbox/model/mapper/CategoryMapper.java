package ru.collbox.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    //@Mapping(source = "user.id", target = "userId")
    CategoryDto toCategoryDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category updateCategory(@MappingTarget Category category, CategoryDto categoryDto);
}
