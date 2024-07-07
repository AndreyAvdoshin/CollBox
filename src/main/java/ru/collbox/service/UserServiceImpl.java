package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.UserDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.model.User;
import ru.collbox.model.mapper.UserMapper;
import ru.collbox.repository.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.toUser(userDto);

        log.info("Создание пользователя - {}", user);
        user = repository.save(user);
        return mapper.toUserDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = returnIfExists(userId);
        user = mapper.updateUser(user, userDto);

        log.info("Обновление пользователя - {}", user);
        user = repository.save(user);

        return mapper.toUserDto(user);
    }

    @Override
    public UserDto getByIdUser(Long userId) {
        log.info("Получение пользователя по инидификатору userId = {}", userId);
        User user = returnIfExists(userId);
        return mapper.toUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        log.info("Удаление пользователя по инидификатору userId = {}", userId);
        User user = returnIfExists(userId);
        repository.delete(user);
    }

    @Override
    public User returnIfExists(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
    }

    @Override
    public void checkExistingUser(Long userId) {
        boolean exist = repository.existsById(userId);
        if (!exist) {
            throw new NotFoundException("User", userId);
        }
    }
}
