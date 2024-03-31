package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collbox.dto.UserDto;
import ru.collbox.model.User;
import ru.collbox.model.mapper.UserMapper;
import ru.collbox.repository.UserRepository;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toUser(userDto);
        log.info("Создание пользователя - {}", user);
        user = repository.save(user);
        return UserMapper.INSTANCE.toUserDto(user);
    }

}
