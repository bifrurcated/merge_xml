package com.vpolosov.trainee.mergexml.validators;

import com.vpolosov.trainee.mergexml.aspect.Loggable;
import com.vpolosov.trainee.mergexml.config.ConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Проверка размера файла.
 *
 * @author Ali Takushinov
 */
@Component
@RequiredArgsConstructor
public class CheckFileSize {

    /**
     * Свойства приложения.
     */
    private final ConfigProperties configProperties;

    /**
     * Проверяет файл на превышение лимитного размера.
     *
     * @param file который будет проверяться.
     * @return {@code true} если файл превышает лимитированного размера, иначе {@code false}.
     */
    @Loggable
    public boolean isMoreThanFiveKb(File file) {
        return file.length() > configProperties.getMaxResultFileWeight().toBytes();
    }
}
