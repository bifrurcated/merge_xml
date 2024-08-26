package com.vpolosov.trainee.mergexml.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vpolosov.trainee.mergexml.dtos.views.ValidationFileHistoryDtoViews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.vpolosov.trainee.mergexml.model.ValidationFileHistory}.
 * ДТО для сущности ValidationFileHistory.
 *
 * @author Samat Hamzin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(ValidationFileHistoryDtoViews.Internal.class)
public class ValidationFileHistoryDto {
    /**
     * Id валидации.
     */
    private UUID validationId;
    /**
     * Имя файла.
     */
    private String fileName;
    /**
     * Путь к документу.
     */
    private String docRef;
    /**
     * Успех/провал валидации.
     */
    private Boolean isSuccess;
    /**
     * Поля, непрошедшие проверку.
     */
    private String failFields;
    /**
     * Дата валидации.
     */
    private LocalDateTime validationDate;

    /**
     * ДТО процесса валидации.
     * Работает через View Jackson что бы в JSON поле было доступно при десериализации.
     */
    @JsonView(ValidationFileHistoryDtoViews.Input.class)
    private ValidationProcessDto validationProcess;

    /**
     * Метод для получения Id процесса валидации.
     * Также работает через View Jackson что бы в JSON поле доступно при сериализации.
     *
     * @return Id Валидации
     */
    @JsonView(ValidationFileHistoryDtoViews.Output.class)
    @JsonProperty("validationProcessId")
    public UUID getValidationProcessId() {
        return validationProcess.getId();
    }
}