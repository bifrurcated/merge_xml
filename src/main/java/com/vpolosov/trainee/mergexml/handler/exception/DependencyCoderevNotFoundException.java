package com.vpolosov.trainee.mergexml.handler.exception;

/**
 * Исключение выбрасываемое при отсутствии информации по.
 *
 * @author Maksim Litvinenko
 */
public class DependencyCoderevNotFoundException extends RuntimeException {

    /**
     * Конструктор с одним параметром.
     *
     * @param message текст ошибки.
     */
    public DependencyCoderevNotFoundException(String message) {
        super(message);
    }
}
