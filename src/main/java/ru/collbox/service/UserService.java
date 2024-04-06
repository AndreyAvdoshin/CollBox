package ru.collbox.service;

import ru.collbox.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    void deleteUser(long userId);

    UserDto getByIdUser(long userId);

    UserDto updateUser(UserDto userDto, long userId) throws Exception;
}
