package com.vpolosov.trainee.mergexml.repository;

import com.vpolosov.trainee.mergexml.model.ValidationProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для сущности {@link ValidationProcess}.
 * Наследуется от {@link JpaRepository}.
 *
 * @author Samat Hamzin
 */
public interface ValidationProcessRepository extends JpaRepository<ValidationProcess, UUID> {
}