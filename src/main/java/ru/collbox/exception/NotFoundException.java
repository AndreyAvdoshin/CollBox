package ru.collbox.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityType, Long id) {
        super(String.format("Объект %s по id - %s не найден", entityType, id));
    }

    public NotFoundException(String entityType, String email) {
        super(String.format("Объект %s по id - %s не найден", entityType, email));
    }

    public NotFoundException(String message) {
        super(message);
    }
}
