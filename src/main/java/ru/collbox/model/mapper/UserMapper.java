package ru.collbox.model.mapper;

import org.mapstruct.*;
import ru.collbox.dto.UserDto;
import ru.collbox.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(@MappingTarget User user, UserDto userDto);
}
