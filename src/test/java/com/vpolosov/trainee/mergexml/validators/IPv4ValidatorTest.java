package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.handler.exception.InvalidIPv4Exception;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки IP адреса")
class IPv4ValidatorTest {

    @Test
    @DisplayName("Успешная валидация при корректном IP адресе")
    void verify_whenIpValid_thenSuccess() {
        var ipv4Validator = Mockito.spy(new IPv4Validator(TestUtil.documentUtil()));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/Ok");
        var listXml = TestUtil.documents(pathToXml);

        listXml.forEach(ipv4Validator::test);

        verify(ipv4Validator, times(10)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при невалидном IP адресе")
    void verify_whenIpInvalid_thenThrowException() {
        var ipv4Validator = Mockito.spy(new IPv4Validator(TestUtil.documentUtil()));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/InvalidIPv4");
        var listXml = TestUtil.documents(pathToXml);

        for (var xmlFile : listXml) {
            assertThrows(InvalidIPv4Exception.class, () -> ipv4Validator.test(xmlFile));
        }

        verify(ipv4Validator, times(10)).test(any(Document.class));
    }
}