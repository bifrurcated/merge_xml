package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.config.ConfigProperties;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMaxAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectMinAmountException;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectValueException;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;

import java.math.BigDecimal;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки минимальной суммы платежа")
class AmountValidatorTest {

    private ConfigProperties configProperties;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        configProperties = new ConfigProperties();
        configProperties.setMinPayment(new BigDecimal("10"));
        configProperties.setMaxPayment(new BigDecimal("1000000"));
    }

    @Test
    @DisplayName("Успешная валидация при корректной сумме платежа")
    void verify_whenMinAmountIsValid_thenSuccess() {
        var minAmountValidator = Mockito.spy(new AmountValidator(configProperties, TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/Ok");
        var documents = TestUtil.documents(pathToXmlFiles);

        for (var xmlFile : documents) {
            minAmountValidator.test(xmlFile);
        }

        verify(minAmountValidator, times(10)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при некорректной минимальной сумме платежа")
    void verify_whenMinAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/MinAmount");
        var documents = TestUtil.documents(pathToXmlFiles);

        assertThrows(IncorrectMinAmountException.class, () -> documents.forEach(minAmountValidator::test));
    }

    @Test
    @DisplayName("Исключение при невалидном значении платежа")
    void verify_whenAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/NotCorrectAmount");
        var documents = TestUtil.documents(pathToXmlFiles);

        assertThrows(IncorrectValueException.class, () -> documents.forEach(minAmountValidator::test));
    }

    @Test
    @DisplayName("Исключение при некорректной максимальной сумме платежа")
    void verify_whenMaxAmountIsNotValid_thenThrowException() {
        var minAmountValidator = spy(new AmountValidator(configProperties, TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/MaxAmount");
        var documents = TestUtil.documents(pathToXmlFiles);

        assertThrows(IncorrectMaxAmountException.class, () -> documents.forEach(minAmountValidator::test));
    }
}