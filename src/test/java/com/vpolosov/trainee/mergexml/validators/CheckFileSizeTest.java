package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.config.ConfigProperties;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.unit.DataSize;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

@DisplayName("Тестирование валидатора для проверки размера файла")
class CheckFileSizeTest {

    private static CheckFileSize checkFileSize;

    private static File tempFile;

    @BeforeAll
    @SneakyThrows
    static void init() {
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setMaxResultFileWeight(DataSize.parse("500KB"));
        checkFileSize = spy(new CheckFileSize(configProperties));
        tempFile = File.createTempFile("total_test_file", ".xml");
    }

    @AfterAll
    @SneakyThrows
    static void deleteTempFile() {
        Files.deleteIfExists(tempFile.toPath());
    }

    @AfterEach
    @SneakyThrows
    void clearTempFile() {
        Files.write(tempFile.toPath(), new byte[0]);
    }

    @Test
    @SneakyThrows
    @DisplayName("Тестирование метода isMoreThanFiveKb при размере файла меньше, чем указано в configProperties")
    void isMoreThanFiveKb_whenValidFileSize_thenReturnFalse() {
        int validFileSize = 1024*300;
        Files.write(tempFile.toPath(), new byte[validFileSize]);

        assertFalse(checkFileSize.isMoreThanFiveKb(tempFile));
    }

    @Test
    @SneakyThrows
    @DisplayName("Тестирование метода isMoreThanFiveKb при размере файла больше, чем указано в configProperties")
    void isMoreThanFiveKb_whenInvalidFileSize_thenReturnTrue() {
        int invalidFileSize = 1024*600;
        Files.write(tempFile.toPath(), new byte[invalidFileSize]);

        assertTrue(checkFileSize.isMoreThanFiveKb(tempFile));
    }
}
