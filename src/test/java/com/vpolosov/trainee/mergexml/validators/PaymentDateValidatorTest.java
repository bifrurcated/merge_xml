package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.config.TimeConfig;
import com.vpolosov.trainee.mergexml.handler.exception.IncorrectDateException;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;

import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для платёжной даты")
class PaymentDateValidatorTest {

    TimeConfig timeConfig;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        timeConfig = new TimeConfig();
    }

    @Test
    @DisplayName("Проверка файлов при совпадающих датах")
    void verify_whenValidXmlFiles_thenSuccess() {
        var fixedSameDate = LocalDate.parse("22.02.2024", timeConfig.localDateFormat())
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC);
        var clock = Clock.fixed(fixedSameDate, ZoneOffset.UTC);
        var paymentDateValidator = Mockito.spy(new PaymentDateValidator(clock, timeConfig.localDateFormat(), TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/Ok");
        var xmlFiles = TestUtil.documents(pathToXmlFiles);

        for (var xmlFile : xmlFiles) {
            paymentDateValidator.test(xmlFile);
        }

        verify(paymentDateValidator, times(10)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при разных датах")
    void verify_whenDateIsDifferent_thenThrowsException() {
        var fixedAnotherDate = LocalDate.parse("23.02.2024", timeConfig.localDateFormat())
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC);
        var clock = Clock.fixed(fixedAnotherDate, ZoneOffset.UTC);
        var paymentDateValidator = Mockito.spy(new PaymentDateValidator(clock, timeConfig.localDateFormat(), TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/Ok");
        var xmlFiles = TestUtil.documents(pathToXmlFiles);

        for (var xmlFile : xmlFiles) {
            assertThrows(IncorrectDateException.class, () -> paymentDateValidator.test(xmlFile));
        }

        verify(paymentDateValidator, times(10)).test(any(Document.class));
    }
}