package com.vpolosov.trainee.mergexml.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "merge-xml")
public class ConfigProperties {

    /**
     * Код валюты.
     */
    private Integer currencyCode;

    /**
     * Срок оплаты.
     */
    private Duration dueDate;

    /**
     * Имя результирующего файла.
     */
    private String fileName;

    /**
     * Максимальное количество файлов.
     */
    private Integer maxCountFile;

    /**
     * Минимальное количество файлов.
     */
    private Integer minCountFile;

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
    private DataSize maxResultFileWeight;
}
