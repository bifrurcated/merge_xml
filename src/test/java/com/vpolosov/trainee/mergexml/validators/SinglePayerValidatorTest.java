package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.handler.exception.DifferentPayerException;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import java.nio.file.Paths;

import static com.vpolosov.trainee.mergexml.utils.XmlTags.PAYER;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки что плательщик один")
class SinglePayerValidatorTest {

    @Test
    @DisplayName("Успешная валидация при одном плательщике")
    void verify_whenSinglePayer_thenSuccess() {
        var singlePayerValidator = spy(new SinglePayerValidator(TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/Ok");
        var xmlFiles = TestUtil.documents(pathToXmlFiles);

        var payer = TestUtil.documentUtil().getValueByTagName(xmlFiles.get(0), PAYER);
        for (var xmlFile : xmlFiles) {
            singlePayerValidator.test(payer, xmlFile);
        }

        verify(singlePayerValidator, times(10)).test(any(String.class), any(Document.class));
    }

    @Test
    @DisplayName("Ошибка при разных плательщиках")
    void verify_whenDifferentPayer_thenThrowException() {
        var singlePayerValidator = spy(new SinglePayerValidator(TestUtil.documentUtil()));
        var pathToXmlFiles = Paths.get("src/test/resources/test_fixtures/sourceXml/DifferentPayer");
        var xmlFiles = TestUtil.documents(pathToXmlFiles);

        var payer = TestUtil.documentUtil().getValueByTagName(xmlFiles.get(0), PAYER);

        assertThrows(DifferentPayerException.class, () -> xmlFiles.forEach(xmlFile -> singlePayerValidator.test(payer, xmlFile)));

        verify(singlePayerValidator, atLeastOnce()).test(any(String.class), any(Document.class));
    }
}