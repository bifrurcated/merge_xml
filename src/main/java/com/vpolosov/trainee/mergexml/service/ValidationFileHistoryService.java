package com.vpolosov.trainee.mergexml.service;

import com.vpolosov.trainee.mergexml.dtos.ValidationFileHistoryDto;
import com.vpolosov.trainee.mergexml.mappers.ValidationFileHistoryMapper;
import com.vpolosov.trainee.mergexml.repository.ValidationFileHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationFileHistoryService {

    private final ValidationFileHistoryRepository repository;
    private final ValidationFileHistoryMapper mapper;

    public List<ValidationFileHistoryDto> getAllValidatedFiles() {
        return mapper.toDtoList(repository.findAllByOrderByValidationDateDesc());
    }
}
