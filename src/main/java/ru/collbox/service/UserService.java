package ru.collbox.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.collbox.dto.AuthRequest;
import ru.collbox.dto.UserDto;
import ru.collbox.model.User;

public interface UserService {

    void authenticate(AuthRequest request, HttpServletResponse response);

    void createUser(UserDto userDto, HttpServletResponse response);

    void deleteUser(Long userId);

    UserDto getByIdUser(Long userId);

    UserDto updateUser(UserDto userDto, Long userId);

    User returnIfExists(Long userId);

    void checkExistingUser(Long userId);
}
