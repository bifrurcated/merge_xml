package com.vpolosov.trainee.mergexml.test;

import com.vpolosov.trainee.mergexml.config.XmlConfig;
import com.vpolosov.trainee.mergexml.utils.DocumentUtil;
import com.vpolosov.trainee.mergexml.utils.FileUtil;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Вспомогательный класс для тестов.
 *
 * @author Maksim Litvinenko
 */
public class TestUtil {

    /**
     * Вспомогательный класс для работы с {@link Document}.
     */
    @Getter
    @Accessors(fluent = true)
    private static final DocumentUtil documentUtil;

    /**
     * Вспомогательный класс для работы с файлами.
     */
    private static final FileUtil fileUtil = new FileUtil();

    static {
        try {
            documentUtil = new DocumentUtil(new XmlConfig().documentBuilder());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает список XML {@link Document} по указанному пути.
     *
     * @param path месторасположение xml файлов.
     * @return список XML {@link Document}.
     */
    public static List<Document> documents(Path path) {
        return fileUtil.listXml(path.toAbsolutePath().toString(), 1, 10).stream()
            .map(documentUtil::parse)
            .toList();
    }

    /**
     * Возвращает список XML {@link Document} по указанному пути.
     *
     * @param path путь до xml файла.
     * @return список XML {@link Document}.
     */
    public static Document document(Path path) {
        var file = new File(path.toAbsolutePath().toString());
        return documentUtil.parse(file);
    }
}
