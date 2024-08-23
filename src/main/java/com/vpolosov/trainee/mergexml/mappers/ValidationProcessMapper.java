package com.vpolosov.trainee.mergexml.mappers;

import com.vpolosov.trainee.mergexml.dtos.ValidationProcessDto;
import com.vpolosov.trainee.mergexml.model.ValidationProcess;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ValidationFileHistoryMapper.class}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ValidationProcessMapper {
    ValidationProcess toEntity(ValidationProcessDto validationProcessDto);

    ValidationProcessDto toDto(ValidationProcess validationProcess);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ValidationProcess partialUpdate(ValidationProcessDto validationProcessDto, @MappingTarget ValidationProcess validationProcess);
}
