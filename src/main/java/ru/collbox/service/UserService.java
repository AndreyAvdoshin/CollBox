package ru.collbox.service;

import ru.collbox.dto.AuthRequest;
import ru.collbox.dto.AuthResponse;
import ru.collbox.dto.UserDto;
import ru.collbox.model.User;

public interface UserService {

    AuthResponse authenticate(AuthRequest request);

    AuthResponse createUser(UserDto userDto);

    void deleteUser(Long userId);

    UserDto getByIdUser(Long userId);

    UserDto updateUser(UserDto userDto, Long userId);

    User returnIfExists(Long userId);

    void checkExistingUser(Long userId);
}
