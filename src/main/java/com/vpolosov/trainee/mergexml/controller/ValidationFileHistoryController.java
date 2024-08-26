package com.vpolosov.trainee.mergexml.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vpolosov.trainee.mergexml.dtos.ValidationFileHistoryDto;
import com.vpolosov.trainee.mergexml.dtos.views.ValidationFileHistoryDtoViews;
import com.vpolosov.trainee.mergexml.service.ValidationFileHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API контроллер для получения информации по истории валидации файлов.
 *
 * @author Samat Hamzin
 */
@RestController
@RequestMapping("/fileHistory")
@RequiredArgsConstructor
public class ValidationFileHistoryController {

    /**
     * Сервис по истории валидации файлов.
     *
     */
    private final ValidationFileHistoryService service;


    /**
     * GET: получение полного списка провалидированных файлов.
     *
     * @return Статус ОК и список всех провалидированных файлов.
     */
    @JsonView(ValidationFileHistoryDtoViews.Output.class)
    @GetMapping("/all")
    public ResponseEntity<List<ValidationFileHistoryDto>> getAll() {
        return ResponseEntity.ok(service.getAllValidatedFiles());
    }
}
