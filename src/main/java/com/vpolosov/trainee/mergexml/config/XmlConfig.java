package com.vpolosov.trainee.mergexml.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Конфигурация для XML.
 *
 * @author Maksim Litvinenko
 */
@Configuration
public class XmlConfig {

    /**
     * Номер имени свойства в строке в конфигурационном файле.
     */
    private static final int INDEX_OF_PROPERTY_NAME = 0;

    /**
     * Номер значения свойства в строке в конфигурационном файле.
     */
    private static final int INDEX_OF_PROPERTY_VALUE = 1;

    /**
     * Обозначает максимальное количество значений на которое
     * можно разделить строку в конфигурационном файле по заданному шаблону
     * (в данном случае на 2 - имя свойства и значение).
     */
    private static final int LIMIT_FOR_SPLIT = 2;

    /**
     * Создаёт бин для работы с XML файлом.
     *
     * @return билдер {@link DocumentBuilder}.
     * @throws ParserConfigurationException если невозможно создать DocumentBuilder,
     *                                      удовлетворяющий запрошенной конфигурации.
     */
    @Bean
    public DocumentBuilder documentBuilder() throws ParserConfigurationException {
        var documentBuilderFactory = DocumentBuilderFactory.newDefaultInstance();
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder();
    }

    /**
     * Фабрика для создания объектов типа {@link Transformer}.
     *
     * @return новую фабрику с параметрами по умолчанию.
     */
    @Bean
    public TransformerFactory transformerFactory() {
        return TransformerFactory.newDefaultInstance();
    }

    /**
     * Свойства приложения.
     *
     * @param configPath путь до txt файла с конфигурацией.
     * @param objectMapper предоставляет функции для чтения и записи JSON.
     * @return объект содержащий информацию о свойствах приложения.
     * @throws IOException если проблема низкого уровня ввода-вывода.
     */
    @Bean
    public ConfigProperties configProperties(
        @Value("${merge-xml.config-path}") String configPath,
        ObjectMapper objectMapper) throws IOException {
        try(Stream<String> lines = Files.lines(Paths.get(configPath))) {
            String jsonConfig = lines
                    .map(line -> line.split(" ", LIMIT_FOR_SPLIT))
                    .map(arr->String.format("\"%s\":\"%s\"",arr[INDEX_OF_PROPERTY_NAME], arr[INDEX_OF_PROPERTY_VALUE]))
                    .collect(Collectors.joining(",","{","}"));
            return objectMapper.readValue(jsonConfig, ConfigProperties.class);
        }
    }
}
