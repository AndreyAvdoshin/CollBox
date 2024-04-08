package ru.collbox.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private final HttpStatus status;
    private final String reason;
    private final String message;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;
}
