package com.vpolosov.trainee.mergexml.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.vpolosov.trainee.mergexml.model.ValidationProcess}.
 * ДТО для сущности ValidationProcess.
 *
 * @author Samat Hamzin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProcessDto {
    /**
     * Id процесса валидации.
     */
    private UUID id;
    /**
     * Путь к каталогу с файлами.
     */
    private String dirRef;
    /**
     * Успех/провал валидации.
     */
    private Boolean isSuccess;
    /**
     * Путь к итоговому документу.
     */
    private String totalDocRef;
    /**
     * Дата валидации.
     */
    private LocalDateTime validationProcessDate;
}