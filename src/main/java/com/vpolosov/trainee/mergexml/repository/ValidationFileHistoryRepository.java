package com.vpolosov.trainee.mergexml.repository;

import com.vpolosov.trainee.mergexml.model.ValidationFileHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ValidationFileHistoryRepository extends JpaRepository<ValidationFileHistory, UUID> {
    @EntityGraph(value = "ValidationFileHistory.nodes", type = EntityGraph.EntityGraphType.LOAD)
    List<ValidationFileHistory> findAllByOrderByValidationDateDesc();
}