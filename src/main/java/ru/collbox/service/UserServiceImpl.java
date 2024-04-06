package ru.collbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.UserDto;
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
    public UserDto updateUser(UserDto userDto, long userId){
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("User по id = %s, не существует!", userId)));

        user = mapper.updateUser(user, userDto);
        log.info("Обновление пользователя - {}", user);
        user = repository.save(user);

        return mapper.toUserDto(user);
    }

    @Override
    public UserDto getByIdUser(long userId) {
        log.info("Получение пользователя по инидификатору userId = {}", userId);
        User user = repository.findById(userId).get();
        return mapper.toUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(long userId) {
        log.info("Удаление пользователя по инидификатору userId = {}", userId);
        repository.deleteById(userId);
    }
}
