package com.vpolosov.trainee.mergexml.utils;

import com.vpolosov.trainee.mergexml.handler.exception.FileNotFoundException;
import com.vpolosov.trainee.mergexml.handler.exception.NotExactlyOneXsdFileException;
import com.vpolosov.trainee.mergexml.handler.exception.NotExactlyTenFilesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Тестирование {@link FileUtil}.
 *
 * @author Maksim Litvinenko
 */
@DisplayName("Тестирование вспомогательного класса для работы с файлами")
class FileUtilTest {

    @Test
    @DisplayName("Успешное чтение 10 xml файлов")
    void listXml_whenValidCountFile_thenSuccess() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/Ok").toAbsolutePath().toString();

        fileUtil.listXml(path, 1, 10);

        verify(fileUtil, times(1)).listXml(path, 1, 10);
    }

    @Test
    @DisplayName("Ошибка когда в каталоге больше 10 xml файлом")
    void listXml_whenXmlFilesMoreMaxCountFile_thenThrowException() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/sourceXml/More10").toAbsolutePath().toString();

        assertThrows(NotExactlyTenFilesException.class, () -> fileUtil.listXml(path, 1, 10));

        verify(fileUtil, times(1)).listXml(path, 1, 10);
    }

    @Test
    @DisplayName("Ошибка когда в каталоге нет xml файлов")
    void listXml_whenNoXmlFiles_thenThrowException() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/sourceXml/NoXml").toAbsolutePath().toString();

        assertThrows(NotExactlyTenFilesException.class, () -> fileUtil.listXml(path, 1, 10));

        verify(fileUtil, times(1)).listXml(path, 1, 10);
    }

    @Test
    @DisplayName("Успешное чтение 1 xsd файла")
    void xsd_whenValidCountFile_thenSuccess() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/Ok").toAbsolutePath().toString();

        fileUtil.xsd(path);

        verify(fileUtil, times(1)).xsd(path);
    }

    @Test
    @DisplayName("Ошибка когда в каталоге больше 1 xsd файла")
    void xsd_whenXsdFilesMoreMaxCountFile_thenThrowException() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/sourceXml/TwoXsd").toAbsolutePath().toString();

        assertThrows(NotExactlyOneXsdFileException.class, () -> fileUtil.xsd(path));

        verify(fileUtil, times(1)).xsd(path);
    }

    @Test
    @DisplayName("Ошибка когда в каталоге нет xsd файлов")
    void listXml_whenNoXsdFiles_thenThrowException() {
        var fileUtil = Mockito.spy(new FileUtil());
        String path = Path.of("src/test/resources/test_fixtures/sourceXml/NoXsd").toAbsolutePath().toString();

        assertThrows(NotExactlyOneXsdFileException.class, () -> fileUtil.xsd(path));

        verify(fileUtil, times(1)).xsd(path);
    }

    @Test
    @DisplayName("Успешное удаление существующего файла")
    void delete_whenHasFile_thenSuccessDelete() throws IOException {
        var fileUtil = Mockito.spy(new FileUtil());
        var origin = Path.of("src/test/resources/test_fixtures/sourceXml/ForDeletion/DTO2.v2.xml");
        var copy = Path.of("src/test/resources/test_fixtures/sourceXml/ForDeletion/DTO2.v1.xml");
        Files.copy(origin, copy, StandardCopyOption.REPLACE_EXISTING);
        var file = copy.toFile();

        fileUtil.delete(file);

        verify(fileUtil, times(1)).delete(file);
    }

    @Test
    @DisplayName("Ошибка удаления при несуществующем файле")
    void delete_whenFileNotFound_thenThrowException() {
        var fileUtil = Mockito.spy(new FileUtil());
        var testFile = new File("temp.xml");

        assertThrows(FileNotFoundException.class, () -> fileUtil.delete(testFile));

        verify(fileUtil, times(1)).delete(testFile);
    }
}