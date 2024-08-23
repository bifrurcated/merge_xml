package com.vpolosov.trainee.mergexml.repository;

import com.vpolosov.trainee.mergexml.model.ValidationProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ValidationProcessRepository extends JpaRepository<ValidationProcess, UUID> {
}