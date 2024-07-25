package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.config.XmlConfig;
import com.vpolosov.trainee.mergexml.handler.exception.InvalidIPv4Exception;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import com.vpolosov.trainee.mergexml.utils.FileUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки IP адреса")
class IPv4ValidatorTest {

    DocumentUtil documentUtil;
    FileUtil fileUtil;

    @BeforeEach
    void setUp() throws Exception {
        documentUtil = new DocumentUtil(new XmlConfig().documentBuilder());
        fileUtil = new FileUtil();
    }

    @Test
    @DisplayName("Успешная валидация при корректном IP адресе")
    void verify_whenIpValid_thenSuccess() {
        var ipv4Validator = Mockito.spy(new IPv4Validator(documentUtil));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/Ok")
            .toAbsolutePath().toString();
        var listXml = fileUtil.listXml(pathToXml, 1, 10);

        listXml.forEach(ipv4Validator::test);

        verify(ipv4Validator, times(10)).test(any(File.class));
    }

    @Test
    @DisplayName("Исключение при невалидном IP адресе")
    void verify_whenIpInvalid_thenThrowException() {
        var ipv4Validator = Mockito.spy(new IPv4Validator(documentUtil));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/InvalidIPv4")
            .toAbsolutePath().toString();
        var listXml = fileUtil.listXml(pathToXml, 1, 10);

        for (var xmlFile : listXml) {
            assertThrows(InvalidIPv4Exception.class, () -> ipv4Validator.test(xmlFile));
        }

        verify(ipv4Validator, times(10)).test(any(File.class));
    }
}