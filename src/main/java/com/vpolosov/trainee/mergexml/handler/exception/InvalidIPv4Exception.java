package com.vpolosov.trainee.mergexml.handler.exception;

/**
 * Исключение выбрасываемое при невалидном IP адресе.
 *
 * @author Maksim Litvinenko
 */
public class InvalidIPv4Exception extends RuntimeException {

    /**
     * Конструктор с одним параметром.
     *
     * @param message текст ошибки.
     */
    public InvalidIPv4Exception(String message) {
        super(message);
    }
}
