package com.vpolosov.trainee.mergexml.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация спецификации для описания REST API.
 *
 * @author Tatyana Reutskaya
 */
@Configuration
public class OpenApiConfig {
    /**
     * Создаёт бин настройки отображения информации о проекте в OpenAPI.
     *
     * @param title наименование проекта.
     *              Задается через тег name в {@code pom.xml}
     *
     * @param version версия проекта.
     *                Задается через тег version в {@code pom.xml}
     *
     * @param description описание проекта.
     *                    Задается через тег description в {@code pom.xml}
     * @return Конфигурация спецификации для описания проекта.
     */
    @Bean
    public OpenAPI openAPI(@Value("${openapi.title}") String title,
                                 @Value("${openapi.version}") String version,
                                 @Value("${openapi.description}") String description) {
        return new OpenAPI().info(new Info()
                .title(title)
                .version(version)
                .description(description));
    }
}
