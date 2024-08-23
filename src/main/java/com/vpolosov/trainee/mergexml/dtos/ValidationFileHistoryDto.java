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
 * DTO for {@link com.vpolosov.trainee.mergexml.model.ValidationFileHistory}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView(ValidationFileHistoryDtoViews.Internal.class)
public class ValidationFileHistoryDto {
    private UUID validationId;
    private String fileName;
    private String docRef;
    private Boolean isSuccess;
    private String failFields;
    private LocalDateTime validationDate;

    @JsonView(ValidationFileHistoryDtoViews.Input.class)
    private ValidationProcessDto validationProcess;

    @JsonView(ValidationFileHistoryDtoViews.Output.class)
    @JsonProperty("validationProcessId")
    public UUID getValidationProcessId() {
        return validationProcess.getId();
    }
}