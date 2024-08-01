package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.config.GraphConfig;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyCoderevNotFoundException;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyPayGrndParamNotFoundException;
import com.vpolosov.trainee.mergexml.handler.exception.NoSingleDependencyPayInfoException;
import com.vpolosov.trainee.mergexml.handler.exception.DependencyPayTypeParamNotFoundException;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import com.vpolosov.trainee.mergexml.utils.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тестирование валидатора для проверки кода программы доходов бюджетов, типа платежа и основания платежа")
class PayInfoValidatorTest {

    private static Graph<Vertex, DefaultEdge> graph;

    @BeforeAll
    static void setUp() {
        graph = new GraphConfig().graph();
    }

    @Test
    @DisplayName("Успешная валидация при корректных входных данных")
    void verify_whenPayInfoValid_thenSuccess() {
        var payInfoValidator = Mockito.spy(new PayInfoValidator(TestUtil.documentUtil(), graph));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/Ok");
        var listXml = TestUtil.documents(pathToXml);

        listXml.forEach(payInfoValidator::test);

        verify(payInfoValidator, times(10)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при отсутствии кода программ доходов бюджетов")
    void verify_whenCoderevNotFound_thenThrowException() {
        var payInfoValidator = Mockito.spy(new PayInfoValidator(TestUtil.documentUtil(), graph));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/DependencyPayInfoIncorrect/DTO1.v1.xml");
        var document = TestUtil.document(pathToXml);

        assertThrows(DependencyCoderevNotFoundException.class, () -> payInfoValidator.test(document));

        verify(payInfoValidator, times(1)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при отсутствии типа платежа")
    void verify_whenPayTypeParamNotFound_thenThrowException() {
        var payInfoValidator = Mockito.spy(new PayInfoValidator(TestUtil.documentUtil(), graph));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/DependencyPayInfoIncorrect/DTO2.v2.xml");
        var document = TestUtil.document(pathToXml);

        assertThrows(DependencyPayTypeParamNotFoundException.class, () -> payInfoValidator.test(document));

        verify(payInfoValidator, times(1)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при отсутствии основания платежа")
    void verify_whenPayGrndParamNotFound_thenThrowException() {
        var payInfoValidator = Mockito.spy(new PayInfoValidator(TestUtil.documentUtil(), graph));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/DependencyPayInfoIncorrect/DTO2.v3.xml");
        var document = TestUtil.document(pathToXml);

        assertThrows(DependencyPayGrndParamNotFoundException.class, () -> payInfoValidator.test(document));

        verify(payInfoValidator, times(1)).test(any(Document.class));
    }

    @Test
    @DisplayName("Исключение при отсутствии связи")
    void verify_whenNotDependencyPayInfo_thenThrowException() {
        var payInfoValidator = Mockito.spy(new PayInfoValidator(TestUtil.documentUtil(), graph));
        var pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/DependencyPayInfoIncorrect/DTO2.v4.xml");
        var document = TestUtil.document(pathToXml);

        assertThrows(NoSingleDependencyPayInfoException.class, () -> payInfoValidator.test(document));

        verify(payInfoValidator, times(1)).test(any(Document.class));
    }
}