package com.vpolosov.trainee.mergexml.mappers;

import com.vpolosov.trainee.mergexml.dtos.ValidationFileHistoryDto;
import com.vpolosov.trainee.mergexml.model.ValidationFileHistory;
import org.mapstruct.BeanMapping;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Маппер для сущности {@link ValidationFileHistory} и дто {@link ValidationFileHistoryDto}.
 *
 * @author Samat Hamzin
 */
@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ValidationProcessMapper.class,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ValidationFileHistoryMapper {
    /**
     * Преобразование из ДТО в сущность.
     *
     * @param validationFileHistoryDto ДТО истории валидации файла.
     * @return ValidationFileHistory сущность.
     */
    ValidationFileHistory toEntity(ValidationFileHistoryDto validationFileHistoryDto);

    /**
     * Преобразование из сущности в ДТО.
     *
     * @param validationFileHistory сущность истории валидации файла.
     * @return ValidationFileHistoryDto Дто.
     */
    ValidationFileHistoryDto toDto(ValidationFileHistory validationFileHistory);

    /**
     * Преобразование списка ДТО в список сущностей.
     *
     * @param validationFileHistoryDtoList список ДТО.
     * @return Список сущностей.
     */
    List<ValidationFileHistory> toEntityList(List<ValidationFileHistoryDto> validationFileHistoryDtoList);

    /**
     * Преобразование списка сущностей в список ДТО.
     *
     * @param validationFileHistoryList список сущностей.
     * @return Список ДТО.
     */
    List<ValidationFileHistoryDto> toDtoList(List<ValidationFileHistory> validationFileHistoryList);

    /**
     * Частичный маппинг в сущность.
     *
     * @param validationFileHistoryDto ДТО
     * @param validationFileHistory Сущность
     * @return Сущность.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ValidationFileHistory partialUpdate(
            ValidationFileHistoryDto validationFileHistoryDto,
            @MappingTarget ValidationFileHistory validationFileHistory
    );
}
