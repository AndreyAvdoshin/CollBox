package ru.collbox.model.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.CategoryDto;
import ru.collbox.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class})
public interface CategoryMapper {


    //@Mapping(target = "user.id", source = "userId")
    Category toCategory(CategoryDto categoryDto, Long userId);

    //@Mapping(target = "userId", source = "user.id")
    CategoryDto toCategoryDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category updateCategory(@MappingTarget Category category, CategoryDto categoryDto);
}
