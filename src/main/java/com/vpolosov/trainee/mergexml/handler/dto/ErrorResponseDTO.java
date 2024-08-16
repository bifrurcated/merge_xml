package com.vpolosov.trainee.mergexml.handler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing an outgoing error response.
 * This class encapsulates information about an error that occurred in the application.
 *
 * @author Ali Takushinov
 */
@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Сообщение об ошибке.")
public class ErrorResponseDTO {

    /**
     * The URI where the error occurred.
     */
    @Schema(description = "URL по которому возникла ошибка.",
            example = "/xml")
    private String uri;

    /**
     * The type of the error.
     */
    @Schema(description = "Тип ошибки.",
            example = "Bad Request")
    private String type;

    /**
     * A descriptive message detailing the error.
     */
    @Schema(description = "Более детальная информация о возникшей ошибке.",
            example = "Дата платежного документа должна быть равна текущей дате")
    private String message;

    /**
     * The date and time when the error occurred.
     */
    @Schema(description = "Время возникновения ошибки.",
            example = "2024-08-16T14:47:23.359551800")
    private String timestamp;

    /**
     * Конструктор с двумя параметрами.
     *
     * @param type    тип ошибки.
     * @param message текст ошибки.
     */
    public ErrorResponseDTO(String type, String message) {
        this.uri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        this.timestamp = LocalDateTime.now().toString();
        this.type = type;
        this.message = message;
    }
}
