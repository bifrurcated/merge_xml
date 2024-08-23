package com.vpolosov.trainee.mergexml.mappers;

import com.vpolosov.trainee.mergexml.dtos.ValidationFileHistoryDto;
import com.vpolosov.trainee.mergexml.model.ValidationFileHistory;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {ValidationProcessMapper.class}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ValidationFileHistoryMapper {

    ValidationFileHistory toEntity(ValidationFileHistoryDto validationFileHistoryDto);

    ValidationFileHistoryDto toDto(ValidationFileHistory validationFileHistory);

    List<ValidationFileHistory> toEntityList(List<ValidationFileHistoryDto> validationFileHistoryDtoList);
    List<ValidationFileHistoryDto> toDtoList(List<ValidationFileHistory> validationFileHistoryList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ValidationFileHistory partialUpdate(ValidationFileHistoryDto validationFileHistoryDto, @MappingTarget ValidationFileHistory validationFileHistory);
}
