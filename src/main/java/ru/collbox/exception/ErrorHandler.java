package ru.collbox.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundException(final NotFoundException e) {
        log.error("Вызвана ошибка Значение не найдено - {}", e.getLocalizedMessage());

        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .reason("Запращиваемый объект не найден")
                .message(e.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
