package ru.collbox.service;

import ru.collbox.dto.UserDto;
import ru.collbox.model.User;

public interface UserService {

    UserDto createUser(UserDto userDto);

    void deleteUser(Long userId);

    UserDto getByIdUser(Long userId);

    UserDto updateUser(UserDto userDto, Long userId);

    User returnIfExists(Long userId);
}
