package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.UserDto;
import ru.collbox.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
