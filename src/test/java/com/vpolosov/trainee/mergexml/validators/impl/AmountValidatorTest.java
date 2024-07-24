package com.vpolosov.trainee.mergexml.validators.impl;

import com.vpolosov.trainee.mergexml.config.ConfigProperties;
import com.vpolosov.trainee.mergexml.config.XmlConfig;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMaxAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMinAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectValueException;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import com.vpolosov.trainee.mergexml.utils.FileUtil;
import com.vpolosov.trainee.mergexml.validators.AmountValidator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки минимальной суммы платежа")
class AmountValidatorTest {

    private FileUtil fileUtil;
    private DocumentUtil documentUtil;
    private ConfigProperties configProperties;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        fileUtil = new FileUtil();
        documentUtil = new DocumentUtil(new XmlConfig().documentBuilder());
        configProperties = new ConfigProperties();
        configProperties.setMinCountFile(1);
        configProperties.setMaxCountFile(10);
        configProperties.setMinPayment(new BigDecimal("10"));
        configProperties.setMaxPayment(new BigDecimal("1000000"));
    }

    @Test
    @DisplayName("Успешная валидация при корректной сумме платежа")
    void verify_whenMinAmountIsValid_thenSuccess() {
        var minAmountValidator = Mockito.spy(new AmountValidator(configProperties, documentUtil));
        var xmlTestFiles = Paths.get("src/test/resources/test_fixtures/Ok");
        var path = xmlTestFiles.toAbsolutePath().toString();
        var xmlFiles = fileUtil.listXml(path, configProperties.getMinCountFile(), configProperties.getMaxCountFile());

        for (var xmlFile : xmlFiles) {
            minAmountValidator.test(xmlFile);
        }

        verify(minAmountValidator, times(10)).test(any(File.class));
    }

    @Test
    @DisplayName("Исключение при некорректной минимальной сумме платежа")
    void verify_whenMinAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, documentUtil));
        var xmlTestFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/MinAmount");
        var path = xmlTestFiles.toAbsolutePath().toString();
        var xmlFiles = fileUtil.listXml(path, configProperties.getMinCountFile(), configProperties.getMaxCountFile());

        assertThrows(IncorrectMinAmountException.class, () -> xmlFiles.forEach(minAmountValidator::test));
    }

    @Test
    @DisplayName("Исключение при невалидном значении платежа")
    void verify_whenAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, documentUtil));
        var xmlTestFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/NotCorrectAmount");
        var path = xmlTestFiles.toAbsolutePath().toString();
        var xmlFiles = fileUtil.listXml(path, configProperties.getMinCountFile(), configProperties.getMaxCountFile());

        assertThrows(IncorrectValueException.class, () -> xmlFiles.forEach(minAmountValidator::test));
    }

    @Test
    @DisplayName("Исключение при некорректной максимальной сумме платежа")
    void verify_whenMaxAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, documentUtil));
        var xmlTestFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/MaxAmount");
        var path = xmlTestFiles.toAbsolutePath().toString();
        var xmlFiles = fileUtil.listXml(path, configProperties.getMinCountFile(), configProperties.getMaxCountFile());

        assertThrows(IncorrectMaxAmountException.class, () -> xmlFiles.forEach(minAmountValidator::test));
    }
}