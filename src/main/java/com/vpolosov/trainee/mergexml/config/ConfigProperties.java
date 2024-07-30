package com.vpolosov.trainee.mergexml.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.unit.DataSize;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * Свойства приложения.
 *
 * @author Maksim Litvinenko
 */
@Getter
@Setter
public class ConfigProperties {

    /**
     * Код валюты.
     */
    private Integer currencyCode;

    /**
     * Срок оплаты.
     */
    @JsonDeserialize(using = DueDateDeserializer.class)
    @JsonProperty("DUEDATE")
    private Duration dueDate;

    /**
     * Имя результирующего файла.
     */
    private String fileName;

    /**
     * Максимальное количество файлов.
     */
    private Integer maxCountFiles;

    /**
     * Минимальное количество файлов.
     */
    private Integer minCountFiles;

    /**
     * Минимальный платёж.
     */
    private BigDecimal minPayment;

    /**
     * Максимальный платёж.
     */
    private BigDecimal maxPayment;

    /**
     * Максимальный размер одного файла.
     */
    @JsonDeserialize(using = DataSizeDeserializer.class)
    private DataSize maxResultFileWeight;
}
