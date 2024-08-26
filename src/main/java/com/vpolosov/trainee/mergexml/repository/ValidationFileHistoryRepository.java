package com.vpolosov.trainee.mergexml.repository;

import com.vpolosov.trainee.mergexml.model.ValidationFileHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для сущности {@link ValidationFileHistory}.
 * Наследуется от {@link JpaRepository}.
 *
 * @author Samat Hamzin
 */
public interface ValidationFileHistoryRepository extends JpaRepository<ValidationFileHistory, UUID> {
    /**
     * Метод для поиска всех провалидируемых файлов, отсортированных по дате валидации по убыванию.
     * Использован EntityGraph для решения проблемы N+1.
     *
     * @return Список провалидированных файлов, отсортированных в порядке убывания по дате валидации.
     */
    @EntityGraph(value = "ValidationFileHistory.nodes", type = EntityGraph.EntityGraphType.LOAD)
    List<ValidationFileHistory> findAllByOrderByValidationDateDesc();
}