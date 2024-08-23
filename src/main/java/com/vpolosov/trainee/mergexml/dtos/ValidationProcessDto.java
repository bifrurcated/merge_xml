package com.vpolosov.trainee.mergexml.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.vpolosov.trainee.mergexml.model.ValidationProcess}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProcessDto {
    private UUID id;
    private String dirRef;
    private Boolean isSuccess;
    private String totalDocRef;
    private LocalDateTime validationProcessDate;
}