package com.vpolosov.trainee.mergexml.mappers;

import com.vpolosov.trainee.mergexml.dtos.ValidationProcessDto;
import com.vpolosov.trainee.mergexml.model.ValidationProcess;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * Маппер для сущности {@link ValidationProcess} и дто {@link ValidationProcessDto}.
 *
 * @author Samat Hamzin
 */
@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ValidationFileHistoryMapper.class,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ValidationProcessMapper {
    /**
     * Преобразование ДТО процесса валидации в сущность.
     *
     * @param validationProcessDto ДТО
     * @return Сущность
     */
    ValidationProcess toEntity(ValidationProcessDto validationProcessDto);

    /**
     * Преобразование сущности процесса валидации в ДТО.
     *
     * @param validationProcess Сущность.
     * @return ДТО
     */
    ValidationProcessDto toDto(ValidationProcess validationProcess);

    /**
     * Частичный маппинг в сущность.
     *
     * @param validationProcessDto ДТО.
     * @param validationProcess Сущность.
     * @return Сущность.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ValidationProcess partialUpdate(
            ValidationProcessDto validationProcessDto,
            @MappingTarget ValidationProcess validationProcess
    );
}
