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
import java.io.File;
import java.io.IOException;

/**
 * Конфигурация для XML.
 *
 * @author Maksim Litvinenko
 */
@Configuration
public class XmlConfig {

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
     * @param configPath путь до JSON файла с конфигурацией.
     * @param objectMapper предоставляет функции для чтения и записи JSON.
     * @return объект содержащий информацию о свойствах приложения.
     * @throws IOException если проблема низкого уровня ввода-вывода.
     */
    @Bean
    public ConfigProperties configProperties(
        @Value("${merge-xml.config-path}") String configPath,
        ObjectMapper objectMapper) throws IOException {
        var jsonFile = new File(configPath);
        return objectMapper.readValue(jsonFile, ConfigProperties.class);
    }
}
