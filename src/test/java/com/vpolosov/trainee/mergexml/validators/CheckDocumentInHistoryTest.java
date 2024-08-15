package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.handler.exception.DuplicationProcessingException;
import com.vpolosov.trainee.mergexml.model.History;
import com.vpolosov.trainee.mergexml.service.HistoryService;
import com.vpolosov.trainee.mergexml.test.TestUtil;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Тестирование проверки документа в истории совершённых платежей.")
@ExtendWith(MockitoExtension.class)
class CheckDocumentInHistoryTest {

    @Mock
    private HistoryService historyService;

    @Spy
    private Logger loggerForDouble;

    @Spy
    private static DocumentUtil documentUtil;

    @InjectMocks
    private CheckDocumentInHistory checkDocumentInHistory;

    private static Document document;

    @BeforeAll
    static void init() {
        documentUtil = TestUtil.documentUtil();
        Path pathToXml = Paths.get("src/test/resources/test_fixtures/sourceXml/Ok/DTO1.v1.xml");
        document = TestUtil.document(pathToXml);
    }

    @Test
    @DisplayName("Тестирование метода test если файл не был обработан ранее")
    void test_whenHistoryIsEmpty_thenReturnTrue() {
        when(historyService.getHistoryListBySpec(any(Specification.class)))
                .thenReturn(Collections.emptyList());

        assertTrue(checkDocumentInHistory.test(document));
    }

    @Test
    @DisplayName("Тестирование метода test если файл уже был обработан")
    void test_whenDocumentAlreadyUploaded_thenThrowException() {
        History history = new History("1fd63ceb89e44fe8875e1892a1cec5f2",
                LocalDate.parse("22.02.2024", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                LocalDateTime.now().minusMinutes(1));

        when(historyService.getHistoryListBySpec(any(Specification.class)))
                .thenReturn(List.of(history));

        assertThrows(DuplicationProcessingException.class, () -> checkDocumentInHistory.test(document));
    }
}
