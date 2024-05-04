package ru.collbox.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Сведения об ошибке")
public class ApiError {
    @Schema(description = "Код статуса HTTP-ответа", example = "FORBIDDEN")
    private final HttpStatus status;
    @Schema(description = "Общее описание причины ошибки", example = "For the requested operation the conditions are not met.")
    private final String reason;
    @Schema(description = "Сообщение об ошибке", example = "Only pending or canceled events can be changed")
    private final String message;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Дата и время когда произошла ошибка (в формате \"yyyy-MM-dd HH:mm:ss\")", example = "2022-06-09 06:27:23")
    private final LocalDateTime timestamp;
}
