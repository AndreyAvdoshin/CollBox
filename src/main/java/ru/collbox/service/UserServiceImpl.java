package ru.collbox.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.AuthRequest;
import ru.collbox.dto.UserDto;
import ru.collbox.exception.NotFoundException;
import ru.collbox.model.User;
import ru.collbox.model.mapper.UserMapper;
import ru.collbox.repository.UserRepository;
import ru.collbox.utils.CookieUtils;
import ru.collbox.utils.JwtService;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder encoder,
                           JwtService jwtService, AuthenticationManager authManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @Transactional
    @Override
    public void authenticate(AuthRequest request, HttpServletResponse response) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = returnIfExists(request.getEmail());

        user.setLastDateAuthen(LocalDateTime.now());

        user = repository.save(user);

        log.info("Аутентификация пользователя - {}", request);
        log.info("Проверка поля последней даты авторизации user - {}", user);

        HttpCookie cookie = CookieUtils.setJwtCookie(jwtService.generateToken(user));
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        //return AuthResponse.builder().token(jwtService.generateToken(user)).build();
    }

    @Transactional
    @Override
    public void createUser(UserDto userDto, HttpServletResponse response) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = mapper.toUser(userDto);

        user.setActiv(true);
        user.setLastDateAuthen(LocalDateTime.now());

        log.info("Создание пользователя - {}", user);
        user = repository.save(user);

        HttpCookie cookie = CookieUtils.setJwtCookie(jwtService.generateToken(user));
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
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

    private User returnIfExists(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User", email)
        );
    }
}
